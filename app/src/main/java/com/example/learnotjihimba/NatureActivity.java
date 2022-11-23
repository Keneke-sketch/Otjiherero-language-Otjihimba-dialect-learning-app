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

public class NatureActivity extends AppCompatActivity {
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
        words.add(new Word("Cave", "Otjikoyo", R.mipmap.ic_launcher, R.raw.cave));
        words.add(new Word("Farm", "Oresevate", R.mipmap.ic_launcher, R.raw.farm));
        words.add(new Word("Fire", "Omuriro", R.mipmap.ic_launcher, R.raw.fire));
        words.add(new Word("Footprint", "Ondambo", R.mipmap.ic_launcher, R.raw.footprint));
        words.add(new Word("Lake", "Erindi", R.mipmap.ic_launcher, R.raw.lake));
        words.add(new Word("Leaf", "Otjijao", R.mipmap.ic_launcher, R.raw.leaf));
        words.add(new Word("Mountain", "Ondundu", R.mipmap.ic_launcher, R.raw.mountain));
        words.add(new Word("Rock", "Oruuwa", R.mipmap.ic_launcher, R.raw.rock));
        words.add(new Word("Spring", "Oruteni", R.mipmap.ic_launcher, R.raw.spring));
        words.add(new Word("Tree", "Omuti", R.mipmap.ic_launcher, R.raw.tree));
        words.add(new Word("Waterfall", "Epupa", R.mipmap.ic_launcher, R.raw.waterfall));
        words.add(new Word("River", "Ondondu", R.mipmap.ic_launcher, R.raw.river));


        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("NatureActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NatureActivity.this, currWord.getAudioResourceId());
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


