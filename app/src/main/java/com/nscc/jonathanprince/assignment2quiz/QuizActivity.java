package com.nscc.jonathanprince.assignment2quiz;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.util.ArrayList;


//class for main quiz portion of the app.
public class QuizActivity extends Activity {

//variables for the view
    TextView currentQuestion;//=(TextView)findViewById(R.id.currentQuestion);
    TextView questionNumber;//=(TextView)findViewById(R.id.questionNumber);
    TextView txtScore;
    Button btnA;//= (Button) findViewById(R.id.btnA);
    Button btnB;//=(Button)findViewById(R.id.btnB);
    Button btnC;//=(Button)findViewById(R.id.btnC);
    Button btnD;//=(Button)findViewById(R.id.btnD);

    //other variables used.
    Quiz quiz;
    String defaultName = "";//default name, should never come in like this.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        currentQuestion = (TextView) findViewById(R.id.currentQuestion);
        questionNumber = (TextView) findViewById(R.id.questionNumber);
        txtScore=(TextView)findViewById(R.id.txtScore);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            defaultName = extras.getString("NAME");//name entered on first screen.
            Context ctx = this.getApplicationContext();

            //assigns new quiz object
            quiz = new Quiz(ctx);
            firstQuestion();

        }//end if(extras!=null)
        else {//will send back to the first screen if they somehow have no name entered.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }//end onCreate

    private void firstQuestion() {
        quiz.getQuestion(quiz.getQuestionCounter());
       // questionNumber.setText(quiz.getQuestionCounter());
        currentQuestion.setText(quiz.getQuestion(quiz.getQuestionCounter()));
        ArrayList<String> ar= quiz.fourAnswers(currentQuestion.getText().toString());

        try {
            btnA.setText(ar.get(0));
            btnB.setText(ar.get(1));
            btnC.setText(ar.get(2));
            btnD.setText(ar.get(3));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    //does all the work of setting up the next question for the screen
    private void nextQuestion() {


        try {
            quiz.setQuestionCounter();
//           questionNumber.setText(quiz.getQuestionCounter());//this breaks stuff for some unknown reason.
            if (quiz.getQuestionCounter()+1 <= quiz.getQuestionLimit()) {
                //update score
                txtScore.setText("Score: " + quiz.getScore() + " out of " + quiz.getQuestionCounter());

                currentQuestion.setText(quiz.getQuestion(quiz.getQuestionCounter()));
                ArrayList<String> ar= quiz.fourAnswers(currentQuestion.getText().toString());
            try{
                btnA.setText(ar.get(0));
                btnB.setText(ar.get(1));
                btnC.setText(ar.get(2));
                btnD.setText(ar.get(3));
            } catch(Exception e) {
                e.printStackTrace();
            }
            } else {

                //toast detailing the score and echoing the entered name.
                Toast.makeText(QuizActivity.this, "Your final score was: " + quiz.getScore() +
                       " out of " +quiz.getQuestionCounter()+". Good job " + defaultName + "!", Toast.LENGTH_LONG).show();

                quiz.setScore(0);
                quiz.setQuestionCounter(0);
                //send back to first page
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }

        }catch(NullPointerException n){
            n.printStackTrace();
        }


        //update question


        //update buttons
    }

    //onclick listener function inputs selected answer for c
    public void aButton(View v) {
        if (quiz.checkAnswer(btnA.getText().toString(),
                currentQuestion.getText().toString()))
        {
            quiz.addScore();
            nextQuestion();
        } else {
            nextQuestion();
        }
    }//end aButton listener

    //onclick listener function inputs selected answer for a
        //onclick listener function inputs selected answer for b
    public void bButton(View v) {
        if (quiz.checkAnswer(btnB.getText().toString(),
                currentQuestion.getText().toString()))
        {
            quiz.addScore();
            nextQuestion();
        } else {
            nextQuestion();
        }
    }//end bButton listener

    //onclick listener function inputs selected answer for c
    public void cButton(View v) {
        if (quiz.checkAnswer(btnC.getText().toString(),
                currentQuestion.getText().toString()))
        {
            quiz.addScore();
            nextQuestion();
        } else {
            nextQuestion();
        }
    }//end cButton listener

    //onclick listener function inputs selected answer for d
    public void dButton(View v) {
        if (quiz.checkAnswer(btnD.getText().toString(),
                currentQuestion.getText().toString()))
        {
            quiz.addScore();
            nextQuestion();
        } else {
            nextQuestion();
        }
    }//end dButton listener



}//end class
