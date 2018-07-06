package com.example.andrvat.peoplessights;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FavoritesList extends Fragment {


    public FavoritesList() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.toolbar_public.setTitle("Избранное");
        return inflater.inflate(R.layout.favorites_list, container, false);
    }


}

