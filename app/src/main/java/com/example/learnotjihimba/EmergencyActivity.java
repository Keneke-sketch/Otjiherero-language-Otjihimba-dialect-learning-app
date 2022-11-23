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

public class EmergencyActivity extends AppCompatActivity {
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
        words.add(new Word("Help", "Ndjivatera", R.mipmap.ic_launcher_round, R.raw.help));
        words.add(new Word("Please", "Arikana", R.mipmap.ic_launcher, R.raw.please));
        words.add(new Word("Where can I find....?", "o.... meivazapi?", R.drawable.baby, R.raw.wherecanifind));
        words.add(new Word("Fuel station", "oservisa", R.mipmap.ic_launcher, R.raw.fuelstation));
        words.add(new Word("Tyre repair", "onganda pupungurwa omarama", R.mipmap.ic_launcher, R.raw.tyrerepair));
        words.add(new Word("Police station", "opolise", R.mipmap.ic_launcher, R.raw.police));
        words.add(new Word("Hospital", "Otjipangero", R.mipmap.ic_launcher, R.raw.hospital));
        words.add(new Word("Can you please help me?", "Arikana ndjivatera", R.mipmap.ic_launcher, R.raw.canyoupleasehelp));
        words.add(new Word("Fire", "Omuriro", R.mipmap.ic_launcher, R.raw.fire));
        words.add(new Word("I'm lost", "Mbapandjara", R.mipmap.ic_launcher, R.raw.imlost));
        words.add(new Word("Stop", "Stopa", R.mipmap.ic_launcher, R.raw.stop));
        words.add(new Word("My .... is missing", "0.... jandje japandjara", R.mipmap.ic_launcher, R.raw.lost));
        words.add(new Word("Wallet", "ondjatu jovimariva", R.mipmap.ic_launcher, R.raw.wallet));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("EmergencyActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(EmergencyActivity.this, currWord.getAudioResourceId());
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



