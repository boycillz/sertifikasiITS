package com.boycillz.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mahasiswa_db";
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMOR = "nomor";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_TANGGAL_LAHIR = "tanggal_lahir";
    private static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";
    private static final String COLUMN_ALAMAT = "alamat";

    private static final String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NOMOR + " TEXT,"
            + COLUMN_NAMA + " TEXT,"
            + COLUMN_TANGGAL_LAHIR + " TEXT,"
            + COLUMN_JENIS_KELAMIN + " TEXT,"
            + COLUMN_ALAMAT + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, mahasiswa.getNomor());
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_TANGGAL_LAHIR, mahasiswa.getTanggalLahir());
        values.put(COLUMN_JENIS_KELAMIN, mahasiswa.getJenisKelamin());
        values.put(COLUMN_ALAMAT, mahasiswa.getAlamat());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    public int updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, mahasiswa.getNomor());
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_TANGGAL_LAHIR, mahasiswa.getTanggalLahir());
        values.put(COLUMN_JENIS_KELAMIN, mahasiswa.getJenisKelamin());
        values.put(COLUMN_ALAMAT, mahasiswa.getAlamat());

        return db.update(TABLE_MAHASISWA, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
    }

    public void deleteMahasiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                mahasiswa.setNomor(cursor.getString(cursor.getColumnIndex(COLUMN_NOMOR)));
                mahasiswa.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)));
                mahasiswa.setTanggalLahir(cursor.getString(cursor.getColumnIndex(COLUMN_TANGGAL_LAHIR)));
                mahasiswa.setJenisKelamin(cursor.getString(cursor.getColumnIndex(COLUMN_JENIS_KELAMIN)));
                mahasiswa.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_ALAMAT)));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return mahasiswaList;
    }
}
