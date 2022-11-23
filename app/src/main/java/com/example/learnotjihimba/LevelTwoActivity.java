package com.example.learnotjihimba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelTwoActivity extends AppCompatActivity {
    TextView question;
    EditText userAnswer;
    AppCompatButton submit;
    int currentQuestion = 0;
    List<Item> questions;
    int score = 0;
    Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        question = findViewById(R.id.question);
        dialog = new Dialog(LevelTwoActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setTitle("Game over");

        AppCompatButton buttonNo = dialog.findViewById(R.id.buttonNo);
        Button buttonYes = dialog.findViewById(R.id.buttonYes);
        TextView textMessage = dialog.findViewById(R.id.textMessage);
////        textMessage.setText((String.valueOf(score)));
////        textMessage.setText(" Score: " + score + " points ");
////        scoress.setText(score);
////        dialog.setTitle("Hi" + score +"Points");

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelTwoActivity.this, LevelTwoActivity.class));
                finish();

            }
        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        userAnswer = findViewById(R.id.edit);
        submit = findViewById(R.id.submit);
        //Adding questions to list
        questions = new ArrayList<>();
        for (int i = 0; i < audio.questions.length; i++) {
            questions.add(new Item(audio.questions[i], audio.answers[i]));
        }
        Collections.shuffle(questions);
        question.setText(questions.get(currentQuestion).getQuestion());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAnswer.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the answer", Toast.LENGTH_SHORT).show();
                } else{
                    checkAnswer();
                    nextQuestion();
                }
            }
//                if (currentQuestion < audio.questions.length -1) {
//                    currentQuestion++;
//                    question.setText(questions.get(currentQuestion).getQuestion());
//                    userAnswer.setText("");
//                } else {
//                    Toast.makeText(getApplicationContext(), "Entered answer" + userAnswer.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
        });
    }

    private void checkAnswer() {
        if (userAnswer.getText().toString().equalsIgnoreCase(questions.get(currentQuestion).getAnswer()))
        {
            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
            score = score + 1;
        } else {
            String correctAnswer = questions.get(currentQuestion).getAnswer();
            Toast.makeText(getApplicationContext(),"Wrong,correct answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
//          Toast.makeText(getApplicationContext(), "Correct answer is: " + correctAnswer,Toast.LENGTH_SHORT).show();

        }

    }

    @SuppressLint("SetTextI18n")
    private void nextQuestion() {
        if (currentQuestion < audio.questions.length - 1) {
            currentQuestion++;
            question.setText(questions.get(currentQuestion).getQuestion());
            userAnswer.setText("");
//                    score.setText("Score" + score +"/" +questions.length);

        } else {
            submit.setText("Submit");
            TextView textMessage = dialog.findViewById(R.id.textMessage);
            textMessage.setText(" Score: " + score + " points ");


//            AlertDialog.Builder alert = new AlertDialog.Builder(this);
//            alert.setTitle("Game Over");
//            alert.setCancelable(false);
//            alert.setMessage(" Score: " + score + " points ");
//            alert.setPositiveButton("Exit level 1", new DialogInterface.OnClickListener() {
//                @ z*zz
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//
//            alert.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    score = 0;
//                    currentQuestion++;
//                }
//            });

            dialog.show();
        }
    }
}

//    private void finishQuiz() {
//        if (score>2){
//            Toast.makeText(getApplicationContext(), "You passed. Your score is: " + score, Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "You failed. Your score is: " + score, Toast.LENGTH_SHORT).show();
//        }
//    }


//    private void finishQuiz() {
//        String passStatus = "";
//        if (score>5){
//            passStatus="passed";
//        }else {
//            passStatus="failed";
//        }
