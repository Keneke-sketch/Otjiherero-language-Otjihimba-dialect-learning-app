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

public class AnimalsActivity extends AppCompatActivity {
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
        words.add(new Word("Dog", "Ombwa", R.mipmap.ic_launcher_round, R.raw.dog));
        words.add(new Word("Lion", "Ongejama", R.mipmap.ic_launcher, R.raw.lion));
        words.add(new Word("Elephant", "Ondjeu", R.mipmap.ic_launcher, R.raw.elephant));
        words.add(new Word("Zebra", "Ongoro", R.mipmap.ic_launcher, R.raw.ongoro));
        words.add(new Word("Springbok", "Omenje", R.mipmap.ic_launcher, R.raw.sptingbok));
        words.add(new Word("Cow", "Ongombe", R.mipmap.ic_launcher, R.raw.cow));
        words.add(new Word("Goat", "Ongombo", R.mipmap.ic_launcher, R.raw.goat));
        words.add(new Word("Giraffe", "Ombahe", R.mipmap.ic_launcher, R.raw.giraffe));
        words.add(new Word("Does it bite?", "Irumata?", R.mipmap.ic_launcher, R.raw.bite));
        words.add(new Word("Cat", "Okambihi", R.mipmap.ic_launcher, R.raw.cat));
        words.add(new Word("Deer", "Onjati", R.mipmap.ic_launcher, R.raw.onyati));
        words.add(new Word("Eagle", "Orukoze", R.mipmap.ic_launcher, R.raw.orukoze));
        words.add(new Word("Feather", "Einya", R.mipmap.ic_launcher, R.raw.einja));
        words.add(new Word("Horn", "Onja", R.mipmap.ic_launcher, R.raw.onya));
        words.add(new Word("Monkey", "Ondjima", R.mipmap.ic_launcher, R.raw.monkey));
        words.add(new Word("Nest", "Otjiruwo", R.mipmap.ic_launcher, R.raw.nest));
        words.add(new Word("Pet", "Ovitumba vyoponganda", R.mipmap.ic_launcher, R.raw.pet));
        words.add(new Word("Rabbit", "Okapi", R.mipmap.ic_launcher, R.raw.okapi));
        words.add(new Word("Snake", "Otjikokozoke/Onjoka", R.mipmap.ic_launcher, R.raw.snake));
        words.add(new Word("Wolf", "Ombungu", R.mipmap.ic_launcher, R.raw.wolf));
        words.add(new Word("Horse", "Okakambe", R.mipmap.ic_launcher, R.raw.horse));
        words.add(new Word("Lizard", "Okaporo/Eporo", R.mipmap.ic_launcher, R.raw.lizard));
        words.add(new Word("Kudu", "Ohorongo", R.mipmap.ic_launcher, R.raw.kudu));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list_words);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word currWord = words.get(position);
                Log.v("AnimalsActivity", "Current word: " + currWord);

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(AnimalsActivity.this, currWord.getAudioResourceId());
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


