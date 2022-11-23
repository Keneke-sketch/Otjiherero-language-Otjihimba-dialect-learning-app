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

public class ColorsActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
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
        words.add(new Word("Blue", "otjiblou", R.mipmap.ic_launcher_round, R.raw.blue));
        words.add(new Word("Pink", "otjipinke", R.mipmap.ic_launcher, R.raw.pink));
        words.add(new Word("Purple", "otjipepela", R.mipmap.ic_launcher, R.raw.purple));
        words.add(new Word("Grey", "otjigerei", R.mipmap.ic_launcher, R.raw.grey));
        words.add(new Word("Red", "Otjiserandu", R.mipmap.ic_launcher, R.raw.red));
        words.add(new Word("Yellow", "otjigara", R.mipmap.ic_launcher, R.raw.yellow));
        words.add(new Word("Green", "otjigirine", R.mipmap.ic_launcher, R.raw.green));
        words.add(new Word("Black", "otjizoozu", R.mipmap.ic_launcher, R.raw.black));
        words.add(new Word("Brown", "otjibereina", R.mipmap.ic_launcher, R.raw.brown));
        words.add(new Word("Orange", "otjioranga", R.mipmap.ic_launcher, R.raw.orange));
        words.add(new Word("White", "Ombapa", R.mipmap.ic_launcher, R.raw.white));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("ColorsActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, currWord.getAudioResourceId());
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



