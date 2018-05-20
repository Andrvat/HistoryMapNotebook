package com.example.andrvat.peoplessights;

import android.app.IntentService;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;

import java.util.Locale;

public class SpeechService extends IntentService {
    private TextToSpeech textToSpeech;
    private boolean TTSready;
    public SpeechService() {
        super("SpeechService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                TTSready = true;
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
        while (true){
            if(TTSready){
                break;
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        textToSpeech.setLanguage(Locale.getDefault());
        textToSpeech.speak(textForSpeech, TextToSpeech.QUEUE_ADD, null, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
}
