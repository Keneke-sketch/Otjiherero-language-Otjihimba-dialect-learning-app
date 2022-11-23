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

public class NumbersActivity extends AppCompatActivity {
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
        words.add(new Word("One", "Imwe", R.mipmap.ic_launcher, R.raw.imwe));
        words.add(new Word("Two", "Imbari", R.mipmap.ic_launcher, R.raw.mbari));
        words.add(new Word("Three", "Indatu", R.mipmap.ic_launcher, R.raw.ndatu));
        words.add(new Word("Four", "Iine", R.mipmap.ic_launcher, R.raw.ine));
        words.add(new Word("Five", "Indano", R.mipmap.ic_launcher, R.raw.ndano));
        words.add(new Word("Six", "Hamboumwe", R.mipmap.ic_launcher, R.raw.hamboumwe));
        words.add(new Word("Seven", "Hambombari", R.mipmap.ic_launcher, R.raw.seven));
        words.add(new Word("Eight", "Hambondatu", R.mipmap.ic_launcher, R.raw.eight));
        words.add(new Word("Nine", "Imuvju", R.mipmap.ic_launcher, R.raw.nine));
        words.add(new Word("Ten", "Omurongo", R.mipmap.ic_launcher, R.raw.ten));
        words.add(new Word("Eleven", "Omurongo na imwe", R.mipmap.ic_launcher, R.raw.eleven));
        words.add(new Word("Twelve", "Omurongo na mbari", R.mipmap.ic_launcher, R.raw.twelve));


        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("NumbersActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, currWord.getAudioResourceId());
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


