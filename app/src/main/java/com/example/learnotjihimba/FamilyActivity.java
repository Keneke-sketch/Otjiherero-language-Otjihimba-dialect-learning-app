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

public class FamilyActivity extends AppCompatActivity {
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
        words.add(new Word("Mother", "Ina", R.mipmap.ic_launcher, R.raw.mom));
        words.add(new Word("Father", "Ihe", R.mipmap.ic_launcher, R.raw.dad));
        words.add(new Word("Brother", "Omutena", R.mipmap.ic_launcher, R.raw.brosis));
        words.add(new Word("Sister", "Omutena", R.mipmap.ic_launcher, R.raw.brosis));
        words.add(new Word("Uncle", "Omo", R.mipmap.ic_launcher, R.raw.omi));
        words.add(new Word("Aunt", "Inangero/Inatjiveri", R.mipmap.ic_launcher, R.raw.aunt));
        words.add(new Word("Niece", "Omwatje", R.mipmap.ic_launcher, R.raw.child));
        words.add(new Word("Nephew", "Omwatje", R.mipmap.ic_launcher, R.raw.child));
        words.add(new Word("Grandfather", "Ihemukurukaze", R.mipmap.ic_launcher, R.raw.grandad));
        words.add(new Word("Grandmother", "Inamukurukaze", R.mipmap.ic_launcher, R.raw.grandma));
        words.add(new Word("Cousin", "Omuramwe", R.mipmap.ic_launcher, R.raw.cuz));
        words.add(new Word("Friend", "Epanga", R.mipmap.ic_launcher, R.raw.friend));
        words.add(new Word("Child", "Omwatje", R.mipmap.ic_launcher, R.raw.child));
        words.add(new Word("Wife", "Omukazendu", R.mipmap.ic_launcher, R.raw.women));
        words.add(new Word("Husband", "Omurumendu", R.mipmap.ic_launcher, R.raw.man));



        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("FamilyActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, currWord.getAudioResourceId());
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


