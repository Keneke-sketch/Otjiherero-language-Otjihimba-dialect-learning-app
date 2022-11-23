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

public class GreetingsActivity extends AppCompatActivity {
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
        words.add(new Word("Hey", "Kora", R.mipmap.ic_launcher, R.raw.kora));
        words.add(new Word("Good morning", "Moro", R.mipmap.ic_launcher, R.raw.moro));
        words.add(new Word("Good afternoon", "Metaha", R.mipmap.ic_launcher, R.raw.metaha));
        words.add(new Word("Good evening", "Hwenda", R.mipmap.ic_launcher, R.raw.hwenda));
        words.add(new Word("How are you", "Perivi", R.mipmap.ic_launcher, R.raw.perivi));
        words.add(new Word("I am fine and you?", "Mbiri nawa naove?", R.mipmap.ic_launcher, R.raw.mbirinawa));
        words.add(new Word("Good", "Nawa", R.mipmap.ic_launcher, R.raw.nawa));
        words.add(new Word("Bad", "Navi", R.mipmap.ic_launcher, R.raw.navi));
        words.add(new Word("I am not well", "Hiri nawa", R.mipmap.ic_launcher, R.raw.hiringnawa));
        words.add(new Word("My name is....", "Enarandje o...", R.mipmap.ic_launcher, R.raw.ena));
        words.add(new Word("See you later", "Matu hakaene kombunda", R.mipmap.ic_launcher, R.raw.matu));
        words.add(new Word("Goodbye", "Okuvirikiza", R.mipmap.ic_launcher, R.raw.okuviri));
        words.add(new Word("Take care", "Ritjevera", R.mipmap.ic_launcher, R.raw.ritje));
        words.add(new Word("Nice to meet you", "Onawa okuhakaena naove", R.mipmap.ic_launcher, R.raw.onawaokuha));
        words.add(new Word("Can you speak English", "Uhungira otji ingiirisa", R.mipmap.ic_launcher, R.raw.english));
        words.add(new Word("I am from (country)....", "Mbaza ko...", R.mipmap.ic_launcher, R.raw.mbazako));
        words.add(new Word("How old are you?", "Uno zombura ngapi?", R.mipmap.ic_launcher, R.raw.age));
       words.add(new Word("Where can I find", "......metjivaza pi?", R.mipmap.ic_launcher, R.raw.wherecanifind));
        words.add(new Word("May I have some water", "Tupao omeva", R.mipmap.ic_launcher, R.raw.water));


        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("GreetingsActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(GreetingsActivity.this, currWord.getAudioResourceId());
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

