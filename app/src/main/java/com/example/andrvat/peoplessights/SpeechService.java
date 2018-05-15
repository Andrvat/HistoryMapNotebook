package com.example.andrvat.peoplessights;

import android.app.IntentService;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;

import java.util.Locale;

public class SpeechService extends IntentService {
    private TextToSpeech textToSpeech;
    public SpeechService() {
        super("SpeechService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener(){

            @Override
            public void onStart(String s) {

            }

            @Override
            public void onDone(String s) {

            }

            @Override
            public void onError(String s) {

            }
        });
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String textForSpeech = intent.getStringExtra("textForSpeech");
        textToSpeech.setLanguage(Locale.getDefault());
        textToSpeech.speak(textForSpeech, TextToSpeech.QUEUE_ADD, null, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
