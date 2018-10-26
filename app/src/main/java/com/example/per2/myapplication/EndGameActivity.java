package com.example.per2.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class EndGameActivity extends AppCompatActivity {
    private TextView textViewScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String score = getIntent().getStringExtra(MainActivity.EXTRA_SENT_SCORE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        textViewScore = findViewById(R.id.textview_endgameactivity_score);
        textViewScore.setHighlightColor(230);
        textViewScore.setText
                ("Your score is: " + score + "/10");
        if((score.compareTo(String.valueOf(7)))>=0){
            Toast toastGoodJob = Toast.makeText
                    (getApplicationContext(),"Good JOB!",Toast.LENGTH_SHORT);
            toastGoodJob.setMargin(50, 50);
            toastGoodJob.show();
        }
        else if((score.compareTo(String.valueOf(7)))<0 || (score.compareTo(String.valueOf(3))>=0)){
            Toast toastMediocre = Toast.makeText
                    (getApplicationContext(),"You can do better!",Toast.LENGTH_SHORT);
            toastMediocre.setMargin(50, 50);
            toastMediocre.show();
        }
        else{
            Toast toastSad = Toast.makeText
                    (getApplicationContext(),"OMG you are so bad!",Toast.LENGTH_SHORT);
            toastSad.setMargin(50, 50);
            toastSad.show();
        }
    }
}
