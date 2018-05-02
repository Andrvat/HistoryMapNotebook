package com.example.andrvat.peoplessights;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Collections;

public class ActionList extends Fragment {
    public ActionList() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_list, container, false);
        // Inflate the layout for this fragment

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ArrayList<String> actions = dataBaseHelper.getTitles();

        ListView listView = (ListView) view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                actions
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                if (position==0) {

                } else if (position==1) {

                } else if (position==2) {

                } else if (position==3) {

                } else if (position==4) {

                }
            }
        });

        return view;
    }

}