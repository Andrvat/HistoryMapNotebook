package com.example.andrvat.peoplessights;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class CustomMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private MapView mapView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
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


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ArrayList<String> coordination = dataBaseHelper.getCoordinations();
        String info = "Нажмите на окно, чтобы узнать ход событий";
        ArrayList<String> name = dataBaseHelper.getTitles();
        ArrayList<String> colors = dataBaseHelper.getColor();





        // Добавляет маркеры
        for (int i = 0; i < name.size(); i++) { // Циклом проходим по всем маркерам
            String[] coordinationNow = coordination.get(i).split(";"); // Разделяем их координаты по ";"
            double first = Double.parseDouble(coordinationNow[0]);
            double second = Double.parseDouble(coordinationNow[1]);
            LatLng geoCoordination = new LatLng(first, second); // Создаём маркер
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(geoCoordination) // Добавляем маркер на карту
                    .title(name.get(i)) // Название места жирным шрифтом в сплывающем окошке
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)) // Задаём цвет маркеру
                    .snippet(info));// Задаём нижнее описание
            marker.setTag(i); // Задаёт тэг (как будем этот маркер обозначать, чтобы работать с ним)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(geoCoordination));
        }


        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this); // Нужна, чтобы обрабатывать действия при нажатии на окно


        // Добавляет местоположение в текущее время
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    // Тут пишем то, что хотим видеть при нажатии на всплывающее окно
    @Override
    public void onInfoWindowClick(Marker marker) {
        int tag = (int)marker.getTag();
        if (tag == 0) {
            Borodino borodino = new Borodino();
            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerId, borodino);
            transaction.commit();
        } else if (tag == 1) {
            IceBattle iceBattle = new IceBattle();
            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerId, iceBattle);
            transaction.commit();
        } else if (tag == 2) {
            BrusilovBreakthrough brusilovBreakthrough = new BrusilovBreakthrough();
            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerId, brusilovBreakthrough);
            transaction.commit();
        } else if (tag == 3) {
            BattleOfKursk battleOfKursk = new BattleOfKursk();
            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerId, battleOfKursk);
            transaction.commit();
        } else if (tag == 4) {
            StandingOnTheUgra standingOnTheUgra = new StandingOnTheUgra();
            FragmentManager fragmentManager = this.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerId, standingOnTheUgra);
            transaction.commit();
        }

    }

}
