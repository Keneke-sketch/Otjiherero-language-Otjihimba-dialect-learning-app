package com.example.learnotjihimba;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBarDrawerToggle;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView card1, card2, card3;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);

        card1.setOnClickListener((View.OnClickListener) this);
        card2.setOnClickListener((View.OnClickListener) this);
        card3.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.c1:
                i = new Intent(this, MainActivity2.class);
                startActivity(i);
                break;
            case R.id.c2:
                i = new Intent(this, QuizLevel.class);
                startActivity(i);
                break;
            case R.id.c3:
                i = new Intent(this, FactsActivity.class);
                startActivity(i);
                break;
        }
    }
}

