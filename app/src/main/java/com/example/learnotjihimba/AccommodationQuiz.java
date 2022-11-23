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
public class AccommodationQuiz extends AppCompatActivity {

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
                    new answerclass(R.string.question1, R.string.question1A, R.string.question1B, R.string.question1C, R.string.question1D, R.string.answer1),
                    new answerclass(R.string.question2, R.string.question2A, R.string.question2B, R.string.question2C, R.string.question2D, R.string.answer2),
                    new answerclass(R.string.question3, R.string.question3A, R.string.question3B, R.string.question3C, R.string.question3D, R.string.answer3),
                    new answerclass(R.string.question4, R.string.question4A, R.string.question4B, R.string.question4C, R.string.question4D, R.string.answer4),
                    new answerclass(R.string.question5, R.string.question5A, R.string.question5B, R.string.question5C, R.string.question5D, R.string.answer5),


            };

    final int PROGRESS_BAR = (int) Math.ceil(100 / questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_quiz);

        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.simcard);
        songs.add(1, R.raw.nearby);
        songs.add(2, R.raw.phone);
        songs.add(3, R.raw.canileavemybaghere);
        songs.add(4, R.raw.moro);

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(songIndex));

        //Initialize textview
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        volume = findViewById(R.id.volume);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        questionnumber = findViewById(R.id.QuestionNumber);
        selectedOption = findViewById(R.id.selectoption);
        correctanswers = findViewById(R.id.CorrectAnswer);
        progressBar = findViewById(R.id.progress_bar);
        AppCompatButton buttonNo = dialog.findViewById(R.id.buttonNo);
        AppCompatButton buttonYes = dialog.findViewById(R.id.buttonYes);

        dialog = new Dialog(AccommodationQuiz.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccommodationQuiz.this,AccommodationQuiz.class));
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

//        nxtButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateQuestion();
////                if (songIndex<songNum){
////                    songIndex++;
////                    mMediaPlayer = MediaPlayer.create(getApplicationContext(),songs.get(songIndex));
////                    updateQuestion();
////                }
////                else
////                    {
//////                    songIndex=0;
////                    updateQuestion();
////                }
//            }
//        });
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
        if (currentIndex == 0 && songIndex == 4) {
            TextView textMessage = dialog.findViewById(R.id.textMessage);
            textMessage.setText(" Score: " + mscore + " points ");
            dialog.show();

        } else {
            ArrayList<Integer> songs = new ArrayList<>();
            songs.add(0, R.raw.simcard);
            songs.add(1, R.raw.nearby);
            songs.add(2, R.raw.phone);
            songs.add(3, R.raw.canileavemybaghere);
            songs.add(4, R.raw.moro);
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