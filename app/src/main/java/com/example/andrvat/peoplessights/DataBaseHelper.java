package com.example.andrvat.peoplessights;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class DataBaseHelper extends SQLiteOpenHelper{

    private Context context;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context cnt){
        super(cnt, "test_db_info_six", null, 1);
        this.context = cnt;
    }

    //Выполняется при создании базы данных, нужно написать код для создания таблиц
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        AssetManager assetManager = context.getAssets();


        Scanner in;
        try {
            InputStream inputStream = assetManager.open("test.txt");
            in = new Scanner(inputStream);
            while (in.hasNext()) {
                String s = in.nextLine();
                // dfd
                sqLiteDatabase.execSQL(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public ArrayList<String> getCoordinations(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT latitude, longitude FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            double lat = cursor.getDouble(0);
            double lng = cursor.getDouble(1);
            String s = Double.toString(lat) + ";" + Double.toString(lng);
            hashMap.add(s);
        }
        return hashMap;
    }
    public ArrayList<String> getTitles(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT title FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            hashMap.add(cursor.getString(0));
        }
        return hashMap;
    }
    public ArrayList<String> getColor(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT colorMarker FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            hashMap.add(cursor.getString(0));
        }
        return hashMap;
    }
    public HashMap<String, String> getDescription(String id){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT title, descriptionOne, descriptionTwo, pictureOne, pictureTwo FROM actions WHERE _id = ?";
        Cursor cursor = readableDatabase.rawQuery(sql,new String[]{id});
        cursor.moveToNext();
        String title = cursor.getString(0);
        String descriptionOne = cursor.getString(1);
        String descriptionTwo = cursor.getString(2);
        String pictureOne = cursor.getString(3);
        String pictureTwo = cursor.getString(4);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("title", title);
        hashMap.put("descriptionOne", descriptionOne);
        hashMap.put("descriptionTwo", descriptionTwo);
        hashMap.put("pictureOne", pictureOne);
        hashMap.put("pictureTwo", pictureTwo);
        return hashMap;
    }

    public ArrayList<HashMap<String, String>> getAll(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT title, descriptionOne, descriptionTwo, pictureOne, pictureTwo FROM actions";
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String descriptionOne = cursor.getString(1);
            String descriptionTwo = cursor.getString(2);
            String pictureOne = cursor.getString(3);
            String pictureTwo = cursor.getString(4);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("title", title);
            hashMap.put("descriptionOne", descriptionOne);
            hashMap.put("descriptionTwo", descriptionTwo);
            hashMap.put("pictureOne", pictureOne);
            hashMap.put("pictureTwo", pictureTwo);
            arrayList.add(hashMap);
        }
        return arrayList;
    }


}