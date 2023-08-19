package com.boycillz.aplikasikampusku.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    static final String DATABASE_NAME = "digitaltalent.db";
    public static final String TABLE_SQLite = "sqlite";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMOR = "nomor";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TL = "tl";
    public static final String COLUMN_JK = "jk";
    public static final String COLUMN_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override//buat tabel dalam database
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMOR + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_TL + " TEXT NOT NULL, " +
                COLUMN_JK + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    //insert data input user to database
    public void insert(String nomor, String name, String tl, String jk, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, nomor);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TL, tl);
        values.put(COLUMN_JK, jk);
        values.put(COLUMN_ADDRESS, address);

        long id = database.insert(TABLE_SQLite, null, values);
        Log.e("insert sqlite", "ID inserted: " + id);
        database.close();
    }

    //upadate database from sqlite
    public void update(int id, String nomor, String name, String tl, String jk, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, nomor);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TL, tl);
        values.put(COLUMN_JK, jk);
        values.put(COLUMN_ADDRESS, address);

        int rowsAffected = database.update(TABLE_SQLite, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
        Log.e("update sqlite", "Rows affected: " + rowsAffected);
        database.close();
    }

    //hapus database from sqlite
    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        int rowsAffected = database.delete(TABLE_SQLite, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
        Log.e("delete sqlite", "Rows affected: " + rowsAffected);
        database.close();
    }

    //menampilkan database from sqlite
    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NOMOR, cursor.getString(1));
                map.put(COLUMN_NAME, cursor.getString(2));
                map.put(COLUMN_TL, cursor.getString(3));
                map.put(COLUMN_JK, cursor.getString(4));
                map.put(COLUMN_ADDRESS, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.e("select sqlite", "" + wordList);
        database.close();
        return wordList;
    }
}
