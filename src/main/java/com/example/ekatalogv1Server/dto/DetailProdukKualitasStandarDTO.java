package com.example.ekatalogv1Server.dto;

import java.util.Date;

public class DetailProdukKualitasStandarDTO {

    private int delFlag;
    private String namaProduk;
    private Double hargaProduk;
    private String deskripsi;
    private Date tanggal;
    private int stokProduk;

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Double getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(Double hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(int stokProduk) {
        this.stokProduk = stokProduk;
    }
}
