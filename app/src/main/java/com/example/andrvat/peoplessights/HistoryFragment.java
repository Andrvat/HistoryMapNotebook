package com.example.andrvat.peoplessights;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;



public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Bundle bundle = getArguments();
        String id = bundle.getString("id"); // получаем id, на который пользователь нажал
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        HashMap<String,String> information = dataBaseHelper.getDescription(id); // получаем информацию из БД
        HashMap<String, Drawable> getPictures = dataBaseHelper.getPictures(id);
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.fragment_historyfragment, container, false);

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

        return frameLayout;
    }
}
