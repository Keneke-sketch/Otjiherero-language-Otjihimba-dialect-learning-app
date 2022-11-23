package com.example.learnotjihimba;

public class Item {
    String questions, answers;
    public Item(String questions, String answers){
        this.questions = questions;
        this.answers = answers;
    }

    public String getQuestion() {
        return questions;
    }

    public String getAnswer() {
        return answers;
    }
}
