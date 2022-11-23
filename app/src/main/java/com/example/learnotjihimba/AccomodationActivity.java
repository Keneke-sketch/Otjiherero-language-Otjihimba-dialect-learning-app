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

public class AccomodationActivity extends AppCompatActivity {
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
        words.add(new Word("Hotel", "ohotela", R.mipmap.ic_launcher_round, R.raw.hotel));
        words.add(new Word("Campsite", "ocampsita", R.mipmap.ic_launcher, R.raw.campsite));
        words.add(new Word("Where is the nearest campsite?", "O campsita jokopezu iripi?", R.mipmap.ic_launcher, R.raw.whereisthenearestcampsite));
        words.add(new Word("Nearby", "Popezu", R.mipmap.ic_launcher, R.raw.nearby));
        words.add(new Word("Can I leave my bag here?", "Ondjatu jandje mejenene okujesamba?", R.mipmap.ic_launcher, R.raw.canileavemybaghere));
        words.add(new Word("Can I do money exchange here", "Mejenene okuchenga ovimariva mba?", R.mipmap.ic_launcher, R.raw.moneyexchange));
        words.add(new Word("Can I use the internet", "Mejenene okuungurisa ointerneta?", R.mipmap.ic_launcher, R.raw.useinternet));
        words.add(new Word("Can I use the kitchen", "Mejenene okuungurisa okombuisa?", R.mipmap.ic_launcher, R.raw.usekitchen));
        words.add(new Word("Mobile phone", "Ongoze", R.mipmap.ic_launcher, R.raw.phone));
        words.add(new Word("Sim card", "okakarata kongoze", R.mipmap.ic_launcher, R.raw.simcard));
        words.add(new Word("Where can I rent a cellphone?", "Pepipumejenene okujazema ongoze", R.mipmap.ic_launcher, R.raw.moro));
        words.add(new Word("I had a great stay", "Mbari neyuva ewa", R.mipmap.ic_launcher, R.raw.moro));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("AccomodationActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(AccomodationActivity.this, currWord.getAudioResourceId());
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



