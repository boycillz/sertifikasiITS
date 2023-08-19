package com.boycillz.crudapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private int id;
    private String nomor;
    private String nama;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;

    public Mahasiswa() {
        // Constructor kosong diperlukan untuk Parcelable
    }

    protected Mahasiswa(Parcel in) {
        id = in.readInt();
        nomor = in.readString();
        nama = in.readString();
        tanggalLahir = in.readString();
        jenisKelamin = in.readString();
        alamat = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomor);
        dest.writeString(nama);
        dest.writeString(tanggalLahir);
        dest.writeString(jenisKelamin);
        dest.writeString(alamat);
    }
}
