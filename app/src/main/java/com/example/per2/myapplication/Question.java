package com.example.per2.myapplication;

public class Question {
    private String question;
    private boolean answer;

    public Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }

    public boolean getAnswer(){
        return this.answer;
    }

    public void setQuestion(String q){
        this.question = q;
    }
    public void setAnswer(boolean a){
        this.answer = a;
    }

    public boolean checkAnswer(boolean userAnswer){
        if(userAnswer==getAnswer()){
            return true;
        }
        else{
            return false;
        }
    }
}
