package com.nscc.jonathanprince.assignment2quiz;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Quiz {

    private static final int QUESTION_LIMIT = 10;//number of questions to create/allow.

    private ArrayList<String> answer;
    private ArrayList<String> question;
    private Map<String, String> map;


    public void setQuestionCounter(int questionCounter) {
        this.questionCounter = questionCounter;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score = 0;//a variable to keep track of scoring
    private int questionCounter = 0; //variable to keep track of what question the user is on.

    //default constructor
    public Quiz(Context ctx) {

        answer = new ArrayList<String>();//declare my question and answer arrays.
        question = new ArrayList<String>();
        map = new HashMap<String, String>();

        createQuestions(answer, question, map, ctx);

    }

    private void createQuestions(ArrayList<String> answer, ArrayList<String> question, Map<String, String> map, Context ctx) {
        try {

            /*int i = this.getResources().getIdentifier
                    ("questions","raw", this.getPackageName());*/
            InputStream iStream = ctx.getResources().openRawResource(R.raw.quiz);//This seemed to be the magic line to get it to read the file.
            //iStream = ctx.getResources().openRawResource(R.raw.easyquiz);//this line enables debug quiz file
            InputStreamReader iReader = new InputStreamReader(iStream);
            BufferedReader bReader = new BufferedReader(iReader); //

            String entry;
            int count=0;

            while ((entry = bReader.readLine()) != null) {

                //i'm very much aware of how messy this is getting

                String[] result = entry.split(";");//splits the line into two parts. file should only have two results per line.
                answer.add(result[0]);//not using full OOP here
                question.add(result[1]);

                map.put(answer.get(count), getQuestion(count));//sets the hashmap question and answer together
                count++;//adds to be the next question

            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        shuffleQ();

    }

    //mixes order of questions
    private void shuffleQ() {
        long seed = System.nanoTime();
        Collections.shuffle(question, new Random(seed));
        Collections.shuffle(answer, new Random(seed));
    }

    public ArrayList<String> fourAnswers(String qtion){
        //hoping to take actual answer and the top 3 from the shuffled list, then assign them to buttons randomly.


        int ansvalue;
        long seed2=System.nanoTime();
        Random random = new Random(seed2);
        ArrayList<String> ar=new ArrayList<String>();//temporary array to send away
        ArrayList<String> tempAns=answer;//assign temparry the answer array to be scrambled

        Collections.shuffle(tempAns, new Random(seed2));//shuffles the temparry

        ansvalue = random.nextInt(4);


        for (int i = 0; i < 4; i++) {


            if (ansvalue == i) {
                //handles random button placement of the answer(i hope)
                int test = 0;
                do {
                    if (qtion.equals(map.get(answer.get(test)))) {
                        ar.add(answer.get(test));
                    }
                    test++;

                } while (test <= 9);
            } else if (qtion.equals(map.get(answer.get(i)))) {
                ar.add(answer.get(5));//do this to remove duplicate entries of the answer
            } else {
                ar.add(answer.get(i));
            }
        }



        return ar;
    }


    //checks to see if the question matches what is in the hash map for an answer
    public boolean checkAnswer(String ans, String que) {


        return que.equals(map.get(ans));

    }




    public String getQuestion(int num) {
        return question.get(num);
    }


    public int getQuestionLimit() {
        return QUESTION_LIMIT;
    }



    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score = score+1;
    }

    public int getQuestionCounter() {
        return questionCounter;
    }

    public void setQuestionCounter() {
        this.questionCounter = questionCounter +1;
    }
}


