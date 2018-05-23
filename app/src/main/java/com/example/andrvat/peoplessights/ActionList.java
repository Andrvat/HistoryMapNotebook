package com.example.andrvat.peoplessights;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class ActionList extends Fragment {
    SearchView searchView;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView listView;
    public static boolean check_two = false;

    public ActionList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_action_list, container, false);
        // Inflate the layout for this fragment

        MainActivity.toolbar_public.setTitle("Список событий");

        final DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ArrayList<String> actions = dataBaseHelper.getTitles();
        listView = (ListView) view.findViewById(R.id.listView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        userCursor = dataBaseHelper.getTitlesTEST(); // Получаем курсор
        final String[] headers = new String[] {DataBaseHelper.COLUMN_TITLE};
        userAdapter = new SimpleCursorAdapter( // Заполняем ListView, используя SimpleCursorAdapter
                getActivity(),
                android.R.layout.simple_list_item_1,
                userCursor,
                headers,
                new int[] {android.R.id.text1},
                0
                );
        listView.setAdapter(userAdapter);
        // Фильтруем текст
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userCursor = dataBaseHelper.getTitlesSearch(newText);
                final String[] headers = new String[] {DataBaseHelper.COLUMN_TITLE};
                userAdapter = new SimpleCursorAdapter(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        userCursor,
                        headers,
                        new int[] {android.R.id.text1},
                        0
                );
                listView.setAdapter(userAdapter); // Задаём пользователю новый список в зависимости от того,
                // что он написал в SearchView
                return false;
            }
        });

        // Обрабатываем нажатие по элементу ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                ArrayList<String> coordination = dataBaseHelper.getCoordinations();
                String[] coordinationNow = coordination.get((int) id - 1).split(";");
                double lat = Double.parseDouble(coordinationNow[0]);
                double lon = Double.parseDouble(coordinationNow[1]);
                CustomMapFragment customMapFragment = new CustomMapFragment();
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat);
                bundle.putDouble("lon", lon);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerId, customMapFragment);
                transaction.commit();
                if (MainActivity.check==false && check_two == false) {
                    MainActivity.check = true; // Задаёт снэкбар при первом (и только при первом!) открытии фрагмента карты - какой цвет маркера какому периоду соответствует
                    check_two = true;
                    Snackbar snackbar = Snackbar.make(view,"Советский Союз - красные, Российская империя - синие, Русь - жёлтые",Snackbar.LENGTH_LONG);
                    snackbar.setDuration(5000);
                    snackbar.show();
                }
                customMapFragment.setArguments(bundle);

            }
        });
        return view;
    }


}