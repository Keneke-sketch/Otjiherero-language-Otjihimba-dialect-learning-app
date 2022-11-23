package com.example.learnotjihimba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    public CardView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
        card5 = (CardView) findViewById(R.id.c5);
        card6 = (CardView) findViewById(R.id.c6);
        card7 = (CardView) findViewById(R.id.c7);
        card8 = (CardView) findViewById(R.id.c8);
        card9 = (CardView) findViewById(R.id.c9);
        card10 = (CardView) findViewById(R.id.c10);
        card11 = (CardView) findViewById(R.id.c11);
        card12 = (CardView) findViewById(R.id.c12);
        card13 = (CardView) findViewById(R.id.c13);
        card14 = (CardView) findViewById(R.id.c14);
        card15 = (CardView) findViewById(R.id.c15);
        card1.setOnClickListener((View.OnClickListener) this);
        card2.setOnClickListener((View.OnClickListener) this);
        card3.setOnClickListener((View.OnClickListener) this);
        card4.setOnClickListener((View.OnClickListener) this);
        card5.setOnClickListener((View.OnClickListener) this);
        card6.setOnClickListener((View.OnClickListener) this);
        card7.setOnClickListener((View.OnClickListener) this);
        card8.setOnClickListener((View.OnClickListener) this);
        card9.setOnClickListener((View.OnClickListener) this);
        card10.setOnClickListener((View.OnClickListener) this);
        card11.setOnClickListener((View.OnClickListener) this);
        card12.setOnClickListener((View.OnClickListener) this);
        card13.setOnClickListener((View.OnClickListener) this);
        card14.setOnClickListener((View.OnClickListener) this);
        card15.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.c1:
                i = new Intent(this, GreetingsActivity.class);
                startActivity(i);
                break;
            case R.id.c2:
                i = new Intent(this, FoodActivity.class);
                startActivity(i);
                break;
            case R.id.c3:
                i = new Intent(this, NatureActivity.class);
                startActivity(i);
                break;
            case R.id.c4:
                i = new Intent(this, SmallTalkActivity.class);
                startActivity(i);
                break;
            case R.id.c5:
                i = new Intent(this, DaysActivity.class);
                startActivity(i);
                break;
            case R.id.c6:
                i = new Intent(this, PlacesActivity.class);
                startActivity(i);
                break;
            case R.id.c7:
                i = new Intent(this, TimeActivity.class);
                startActivity(i);
                break;
            case R.id.c8:
                i = new Intent(this, ColorsActivity.class);
                startActivity(i);
                break;
            case R.id.c9:
                i = new Intent(this, AnimalsActivity.class);
                startActivity(i);
                break;
            case R.id.c10:
                i = new Intent(this, SeasonsActivity.class);
                startActivity(i);
                break;
            case R.id.c11:
                i = new Intent(this, NumbersActivity.class);
                startActivity(i);
                break;
            case R.id.c12:
                i = new Intent(this, AccomodationActivity.class);
                startActivity(i);
                break;
            case R.id.c13:
                i = new Intent(this, EmergencyActivity.class);
                startActivity(i);
                break;
            case R.id.c14:
                i = new Intent(this, TouristActivity.class);
                startActivity(i);
                break;
            case R.id.c15:
                i = new Intent(this, FamilyActivity.class);
                startActivity(i);
                break;
        }
    }
}