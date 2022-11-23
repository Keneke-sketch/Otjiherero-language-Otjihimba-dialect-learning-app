package com.example.learnotjihimba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Meat", "Onjama", R.mipmap.ic_launcher, R.raw.meat));
        words.add(new Word("Milk", "Omaihi", R.mipmap.ic_launcher, R.raw.milk));
        words.add(new Word("Bread", "Omboroto", R.mipmap.ic_launcher, R.raw.bread));
        words.add(new Word("Macaroni", "Omakoroni", R.mipmap.ic_launcher, R.raw.mac));
        words.add(new Word("Juice", "osapa", R.mipmap.ic_launcher, R.raw.juice));
        words.add(new Word("Apple", "Otjiapela", R.mipmap.ic_launcher, R.raw.apple));
        words.add(new Word("Banana", "otjibanana", R.mipmap.ic_launcher, R.raw.banana));
        words.add(new Word("Butter", "Ombuta", R.mipmap.ic_launcher, R.raw.butter));
        words.add(new Word("Coffee", "Okosiva", R.mipmap.ic_launcher, R.raw.coffee));
        words.add(new Word("Cake", "Otjikuki", R.mipmap.ic_launcher, R.raw.cake));
        words.add(new Word("Lunch", "moro", R.mipmap.ic_launcher, R.raw.lunch));
        words.add(new Word("Potato", "Otjihakautu", R.mipmap.ic_launcher, R.raw.potato));
        words.add(new Word("Sugar", "Outji", R.mipmap.ic_launcher, R.raw.sugar));
        words.add(new Word("Spice", "Ospice", R.mipmap.ic_launcher, R.raw.spice));
        words.add(new Word("Breakfast", "Eriro romuhuka", R.mipmap.ic_launcher, R.raw.breakfast));
        words.add(new Word("Dinner", "Eriro rometaha", R.mipmap.ic_launcher, R.raw.dinner));
        words.add(new Word("Beer", "Ombira", R.mipmap.ic_launcher, R.raw.beer));
        words.add(new Word("Wine", "Ovinuwa", R.mipmap.ic_launcher, R.raw.wine));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("FoodActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FoodActivity.this, currWord.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}

