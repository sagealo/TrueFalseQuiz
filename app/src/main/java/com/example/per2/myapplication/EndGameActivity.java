package com.example.per2.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EndGameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewScore;
    private Button buttonRetry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String score = getIntent().getStringExtra(MainActivity.EXTRA_SENT_SCORE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        textViewScore = findViewById(R.id.textview_endgameactivity_score);
        textViewScore.setHighlightColor(200);
        buttonRetry = findViewById(R.id.button_endgameactivity_retry);
        buttonRetry.setOnClickListener(this);
        textViewScore.setText
                ("Your score is: " + score + "/10");
        if((score.compareTo(String.valueOf(7)))<1){
            Toast toastGoodJob = Toast.makeText
                    (getApplicationContext(),"Good JOB!",Toast.LENGTH_SHORT);
            toastGoodJob.setMargin(50, 50);
            toastGoodJob.show();
        }
        else if((score.compareTo(String.valueOf(7)))>0 || (score.compareTo(String.valueOf(3))==1)){
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_endgameactivity_retry:
                Intent intentRestartGame =
                        new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(intentRestartGame);
        }
    }
}
