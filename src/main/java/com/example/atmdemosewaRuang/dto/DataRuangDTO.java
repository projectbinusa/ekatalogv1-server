package com.example.atmdemosewaRuang.dto;

public class DataRuangDTO {
    private String nomor_ruangan;
    private String tempat;
    private String keterangan;

    public String getNomor_ruangan() {
        return nomor_ruangan;
    }
    public void setNomor_ruangan(String nomor_ruangan) {
        this.nomor_ruangan = nomor_ruangan;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTempat() {
        return tempat;
    }
    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
