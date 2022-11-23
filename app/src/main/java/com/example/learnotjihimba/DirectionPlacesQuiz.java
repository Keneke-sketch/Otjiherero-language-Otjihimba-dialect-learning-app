package com.example.learnotjihimba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DirectionPlacesQuiz extends AppCompatActivity {

    private TextView optionA, optionB, optionC, optionD;
    private TextView questionnumber, question, score;
    private TextView selectedOption, correctanswers;
    int currentIndex;
    int mscore = 0;
    int qn = 1;
    ProgressBar progressBar;
    Dialog dialog;
    static MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    ImageView volume;
    int songNum;
    int songIndex = 0;

    int CurrentQuestion, CurrentOptionA, CurrentOptionB, CurrentOptionC, CurrentOptionD;
    Button nxtButton;


    private answerclass[] questionBank = new answerclass[]
            {

                    new answerclass(R.string.QuESTION1, R.string.QuESTION1A, R.string.QuESTION1B, R.string.QuESTION1C, R.string.QuESTION1D,R.string.AnSWER1),
                    new answerclass(R.string.QuESTION2, R.string.QuESTION2A, R.string.QuESTION2B, R.string.QuESTION2C, R.string.QuESTION2D,R.string.AnSWER2),
                    new answerclass(R.string.QuESTION3, R.string.QuESTION3A, R.string.QuESTION3B, R.string.QuESTION3C, R.string.QuESTION3D,R.string.AnSWER3),


            };

    final int PROGRESS_BAR = (int) Math.ceil(100 / questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_places_quiz);

        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.outjo);
        songs.add(1, R.raw.wind);
        songs.add(2, R.raw.walvis);


        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(songIndex));


        //Initialize textview
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);


        volume = findViewById(R.id.volume);
//        nxtButton = findViewById(R.id.nextBtn);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        questionnumber = findViewById(R.id.QuestionNumber);

        selectedOption = findViewById(R.id.selectoption);
        correctanswers = findViewById(R.id.CorrectAnswer);
        progressBar = findViewById(R.id.progress_bar);

        dialog = new Dialog(DirectionPlacesQuiz.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        AppCompatButton buttonNo = dialog.findViewById(R.id.buttonNo);
        AppCompatButton buttonYes = dialog.findViewById(R.id.buttonYes);

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DirectionPlacesQuiz.this, DirectionPlacesQuiz.class));
                finish();

            }
        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
            }
        });


        CurrentQuestion = questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA = questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB = questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC = questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionA);
                updateQuestion();


            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionB);
                updateQuestion();


            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionC);
                updateQuestion();


            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(CurrentOptionD);
                updateQuestion();


            }
        });
    }

    private void checkAnswer(int userSelection) {
        int correctanswer = questionBank[currentIndex].getAnswerid();

        selectedOption.setText(userSelection);
        correctanswers.setText(correctanswer);
        String m = selectedOption.getText().toString().trim();
        String n = correctanswers.getText().toString().trim();

        if (m.equals(n)) {
            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();

            mscore = mscore + 1;


        } else {
            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion() {

        currentIndex = (currentIndex + 1) % questionBank.length;
        if (currentIndex == 0 && songIndex == 2) {
            TextView textMessage = dialog.findViewById(R.id.textMessage);
            textMessage.setText(" Score: " + mscore + " points ");
            dialog.show();

        } else {
            ArrayList<Integer> songs = new ArrayList<>();
            songs.add(0, R.raw.outjo);
            songs.add(1, R.raw.wind);
            songs.add(2, R.raw.walvis);
            songIndex++;
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(songIndex));

            CurrentQuestion = questionBank[currentIndex].getQuestionid();
            question.setText(CurrentQuestion);
            CurrentOptionA = questionBank[currentIndex].getOptionA();
            optionA.setText(CurrentOptionA);
            CurrentOptionB = questionBank[currentIndex].getOptionB();
            optionB.setText(CurrentOptionB);
            CurrentOptionC = questionBank[currentIndex].getOptionC();
            optionC.setText(CurrentOptionC);
            CurrentOptionD = questionBank[currentIndex].getOptionD();
            optionD.setText(CurrentOptionD);
            qn = qn + 1;

            if (qn <= questionBank.length) {
                questionnumber.setText(qn + "/" + questionBank.length + "Question");
            }
            score.setText("Score" + mscore + "/" + questionBank.length);
            progressBar.incrementProgressBy(PROGRESS_BAR);
        }
    }
}