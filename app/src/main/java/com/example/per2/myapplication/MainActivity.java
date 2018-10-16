package com.example.per2.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "cheese";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getting XML
        initializeQuiz();
        Log.d("Quiz", "onCreate: "+ "Quiz");
    }

    private void initializeQuiz() {
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
}
