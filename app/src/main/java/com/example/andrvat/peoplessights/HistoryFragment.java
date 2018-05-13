package com.example.andrvat.peoplessights;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Teacher on 13.05.2018.
 */

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        HashMap<String,String> information = dataBaseHelper.getDescription(id);
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.fragment_historyfragment, container, false);

        TextView MainTitleView = frameLayout.findViewById(R.id.maintitle);
        MainTitleView.setText(information.get("title"));

        TextView DescriptionOneView = frameLayout.findViewById(R.id.descriptionone);
        DescriptionOneView.setText(information.get("descriptionOne"));

        ImageView pictureOneView = frameLayout.findViewById(R.id.pictureone);
        pictureOneView.setImageResource(R.drawable.bagration1);

        TextView DescriptionTwoView = frameLayout.findViewById(R.id.descriptiontwo);
        DescriptionTwoView.setText(information.get("descriptionTwo"));


        ImageView pictureTwoView = frameLayout.findViewById(R.id.picturetwo);
        pictureTwoView.setImageResource(R.drawable.bagration2);

        return frameLayout;
    }
}
