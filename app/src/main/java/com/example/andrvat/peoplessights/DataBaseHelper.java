package com.example.andrvat.peoplessights;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context){
        super(context, "database.db", null, 1);
    }

    //Выполняется при создании базы данных, нужно написать код для создания таблиц
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery1 = "CREATE TABLE actions (\n" +
                "    _id       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    title STRING,\n" +
                "    latitude  REAL,\n" +
                "    longitude REAL,\n" +
                "    colorMarker STRING,\n" +
                "    descriptionOne TEXT,\n" +
                "    descriptionTwo TEXT,\n" +
                "    pictureOne STRING,\n" +
                "    pictureTwo STRING,\n" +
                "    tag   INTEGER\n" +
                ")";
        sqLiteDatabase.execSQL(sqlQuery1); //Этот метод позволяет выполнить любой SQL-запрос

        String addPit = "INSERT INTO actions (latitude, longitude) VALUES (11.567332, 21.126748)";
        sqLiteDatabase.execSQL(addPit);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



//    public int getId(){
//        SQLiteDatabase readableDatabase = this.getReadableDatabase();
//        String sql = "SELECT _id FROM pits";
//        Cursor cursor = readableDatabase.rawQuery(sql, null);
//        int id = 0;
//        while (cursor.moveToNext()){
//            id = cursor.getInt(0);
//        }
//        return id;
//    }


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
}