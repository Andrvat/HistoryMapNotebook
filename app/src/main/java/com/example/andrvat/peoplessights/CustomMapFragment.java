package com.example.andrvat.peoplessights;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CustomMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private MapView mapView;
    public View setView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setView = inflater.inflate(R.layout.fragment_map, container, false);
        return setView;
    }

    //Ждем событие, когда активность точно создалась
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //получам объект активности для того, чтобы можно было выполнить функцию findViewById
        Activity activity = this.getActivity();
        //Находим элемент
        mapView = activity.findViewById(R.id.mapView);
        //Заставляем загрузиться картам
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        LatLng first_camera = new LatLng(51.512400, 34.816516); // Перемещает камеру поближе к Европе
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(first_camera,0));
        String info = "Нажмите на окно, чтобы узнать ход событий";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ArrayList<HashMap<String,String>> mapArrayList = dataBaseHelper.getAll();
        // Добавляет маркеры
        for (int i = 0; i < mapArrayList.size(); i++) { // Циклом проходим по всем маркерам
            if (mapArrayList.get(i).get("color").equals("Red")) {
                double first = Double.parseDouble(mapArrayList.get(i).get("lat"));
                double second = Double.parseDouble(mapArrayList.get(i).get("lon"));
                LatLng geoCoordination = new LatLng(first, second); // Создаём маркер
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(geoCoordination) // Добавляем маркер на карту
                        .title(mapArrayList.get(i).get("title")) // Название места жирным шрифтом в сплывающем окошке
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)) // Задаём цвет маркеру
                        .snippet(info));// Задаём нижнее описание
                marker.setTag(mapArrayList.get(i).get("id")); // Задаёт тэг (как будем этот маркер обозначать, чтобы работать с ним)
                                                        //  . . .

            } else if (mapArrayList.get(i).get("color").equals("Blue")) {
                double first = Double.parseDouble(mapArrayList.get(i).get("lat"));
                double second = Double.parseDouble(mapArrayList.get(i).get("lon"));
                LatLng geoCoordination = new LatLng(first, second); // Создаём маркер
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(geoCoordination) // Добавляем маркер на карту
                        .title(mapArrayList.get(i).get("title")) // Название места жирным шрифтом в сплывающем окошке
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)) // Задаём цвет маркеру
                        .snippet(info));// Задаём нижнее описание
                marker.setTag(mapArrayList.get(i).get("id")); // Задаёт тэг (как будем этот маркер обозначать, чтобы работать с ним)


            } else if (mapArrayList.get(i).get("color").equals("Yellow")) {
                double first = Double.parseDouble(mapArrayList.get(i).get("lat"));
                double second = Double.parseDouble(mapArrayList.get(i).get("lon"));
                LatLng geoCoordination = new LatLng(first, second); // Создаём маркер
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(geoCoordination) // Добавляем маркер на карту
                        .title(mapArrayList.get(i).get("title")) // Название места жирным шрифтом в сплывающем окошке
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)) // Задаём цвет маркеру
                        .snippet(info));// Задаём нижнее описание
                marker.setTag(mapArrayList.get(i).get("id")); // Задаёт тэг (как будем этот маркер обозначать, чтобы работать с ним)
            }
        }

        Bundle bundle = getArguments();
        if(bundle != null) {
            LatLng geoCoordination = new LatLng(bundle.getDouble("lat"), bundle.getDouble("lon"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geoCoordination,8)); // Перемещаем камеру при нажатии на событие из ListView
        }
        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this); // Нужна, чтобы обрабатывать действия при нажатии на окно


    }

    // Тут пишем то, что хотим видеть при нажатии на всплывающее окно
    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle bundle = new Bundle();
        String tag = String.valueOf(marker.getTag());
        bundle.putString("id",tag);
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.setArguments(bundle);
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.containerId, historyFragment);
        transaction.commit();

    }
}
