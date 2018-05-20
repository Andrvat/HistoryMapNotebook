package com.example.andrvat.peoplessights;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.HashMap;








public class HistoryFragment extends Fragment {

    public String sound = "";
    private TextToSpeech textToSpeech;
    int StopORPlay = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
            }
        });

        Bundle bundle = getArguments();
        String id = bundle.getString("id"); // получаем id, на который пользователь нажал
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        HashMap<String,String> information = dataBaseHelper.getDescription(id); // получаем информацию из БД
        HashMap<String, Drawable> getPictures = dataBaseHelper.getPictures(id);
        View frameLayout = inflater.inflate(R.layout.fragment_historyfragment, container, false);
        FloatingActionButton b =  frameLayout.findViewById(R.id.fab); // Находим кнопку


        TextView MainTitleView = frameLayout.findViewById(R.id.maintitle);
        MainTitleView.setText(information.get("title")); // Задаём заголовок

        TextView DescriptionOneView = frameLayout.findViewById(R.id.descriptionone);
        DescriptionOneView.setText(information.get("descriptionOne")); // Задаём первое описание

        ImageView pictureOneView = frameLayout.findViewById(R.id.pictureone);// Задаём первую картинку
        pictureOneView.setImageDrawable(getPictures.get("pictureOne"));

        TextView DescriptionTwoView = frameLayout.findViewById(R.id.descriptiontwo);
        DescriptionTwoView.setText(information.get("descriptionTwo")); // Задаём второе описание


        ImageView pictureTwoView = frameLayout.findViewById(R.id.picturetwo); // Задаём вторую картинку
        pictureTwoView.setImageDrawable(getPictures.get("pictureTwo"));

        sound = information.get("descriptionOne") + " " + information.get("descriptionTwo"); // Задаем, что говорилка будет проговаривать

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StopORPlay%2==0) { // Если чётное нажатие, то говорим
                    textToSpeech.speak(sound, TextToSpeech.QUEUE_ADD, null, null);
                    StopORPlay++;
                    Toast.makeText(getActivity(),"Чтобы остановить звучание, нажмите на кнопку ещё раз...",Toast.LENGTH_LONG).show();
                } else { // Если нет, то останавливаем
                    textToSpeech.stop();
                    StopORPlay++;
                }

            }
        });
        return frameLayout;
    }


}
