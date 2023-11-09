package com.example.retrovolley.Adapter;

public class GetData {
    String nama = "" , alamat = "", jeniskelamin = "";

    public GetData(String nama, String alamat, String JenisKelamin ) {
        this.nama = nama;
        this.alamat = alamat;
        this.jeniskelamin = JenisKelamin;
    }
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }
}
