package com.example.learnotjihimba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String englishTranslation;
    private String otjihimbaTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    public Word(String engTrans, String otjiTrans, int audioId) {
        englishTranslation = engTrans;
        otjihimbaTranslation = otjiTrans;
        audioResourceId = audioId;
    }

    public Word(String engTrans, String otjiTrans, int imageId, int audioId) {
        englishTranslation = engTrans;
        otjihimbaTranslation = otjiTrans;
        imageResourceId = imageId;
        audioResourceId = audioId;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public String getotjihimbaTranslation() {
        return otjihimbaTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return getImageResourceId() != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "englishTranslation='" + englishTranslation + '\'' +
                ", otjihimbaTranslation='" + otjihimbaTranslation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", audioResourceId=" + audioResourceId +
                '}';
    }

}
