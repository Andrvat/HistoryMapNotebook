package com.example.andrvat.peoplessights;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Teacher on 04.03.2018.
 */

public class ActivityWithInternetRequest extends AppCompatActivity {

    // Здесь хранится название нашего сайта-сервера
    private String site = "http://ya.ru";

    private  String siteContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestDataFromInternet requestDataFromInternet = new RequestDataFromInternet();
        requestDataFromInternet.execute(site);

    }

    private class RequestDataFromInternet extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String siteName = strings[0];
            try {
                URL url = new URL(siteName);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                // Пока есть информация, записываем её в line
                while (true) {
                    String line = bufferedReader.readLine();
                    siteContent += line;
                    if (line==null) {
                        break;
                    }
                }
                System.out.println(siteContent);
                inputStream.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
