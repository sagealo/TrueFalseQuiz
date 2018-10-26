package com.example.per2.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cheese";
    public static final String EXTRA_SENT_SCORE = "Your score is:";
    private Button trueButton;
    private Button falseButton;
    private TextView questionView;
    private TextView scoreView;
    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getting XML

        initializeQuiz();
        wireWidgets();
        setListeners();
        Log.d("Quiz", "onCreate: "+ "Quiz");
        scoreView.setText(String.valueOf(+quiz.getScore()));
        displayFirstQuestion(quiz);


    }

    private void displayFirstQuestion(Quiz quiz) {
        quiz.setCurrentQ(quiz.getCurrentQ());
        questionView.setText(quiz.getQuestion().getQuestionText());

    }

    private void displayNextQuestion(Quiz theQuiz) {

            theQuiz.setCurrentQ(theQuiz.getCurrentQ());
            questionView.setText(theQuiz.getQuestion().getQuestionText());

    }


    private void setListeners() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        trueButton = findViewById(R.id.button_mainactivity_true);
        falseButton = findViewById(R.id.button_mainactivity_false);
        questionView = findViewById(R.id.textview_mainactivity_questiondisplay);
        scoreView = findViewById(R.id.textview_mainactivity_score);

    }

    public void initializeQuiz() {
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);
        String sxml = readTextFile(XmlFileInputStream);
        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions =  gson.fromJson(sxml, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);


    }

    private String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
     @Override
     public void onClick(View view){
         switch(view.getId()) {
             case R.id.button_mainactivity_true:
                 answerQuestion(true);
                 break;
             case R.id.button_mainactivity_false:
                 answerQuestion(false);
                 break;
         }
     }

    private void answerQuestion(boolean b) {
         if(quiz.getQuestion().checkAnswer(b)){

             quiz.increaseScore();
             scoreView.setText(String.valueOf(quiz.getScore()));
             quiz.nextQuestion();
             if(quiz.isThereAnotherQ()){
                displayNextQuestion(quiz);
             }
             else{
                 newActivity();
             }
         }
         else{
             scoreView.setText(String.valueOf(quiz.getScore()));
             quiz.nextQuestion();
             if(quiz.isThereAnotherQ()){
                 displayNextQuestion(quiz);
             }
             else{
                 newActivity();
             }
         }
    }

    private void newActivity() {
        Intent intentFinishGame =
                new Intent(MainActivity.this, EndGameActivity.class);
        intentFinishGame.putExtra(EXTRA_SENT_SCORE, String.valueOf(quiz.getScore()));
        startActivity(intentFinishGame);
    }

}
