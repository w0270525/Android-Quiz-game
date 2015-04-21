package com.nscc.jonathanprince.assignment2quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //button onclicklistener for starting the game.
    public void startQuiz(View v) {

//http://developer.android.com/training/basics/firstapp/starting-activity.html
        EditText editText = (EditText) findViewById(R.id.editName);
        String message = editText.getText().toString();

        if (!message.matches("")) {
            Intent intent = new Intent(this, QuizActivity.class);

            intent.putExtra("NAME", message);
            Toast.makeText(MainActivity.this, "Welcome " + editText.getText() + ", good luck.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Please enter a name for record keeping purposes.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
