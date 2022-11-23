package com.example.learnotjihimba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizLevel extends AppCompatActivity {
    ImageView l1, l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizLevel.this, QuizTypes.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizLevel.this, LevelTwoActivity.class);
                startActivity(intent);
            }
        });

    }
}


