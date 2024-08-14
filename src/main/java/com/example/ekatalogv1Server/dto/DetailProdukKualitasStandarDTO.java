package com.example.ekatalogv1Server.dto;

import java.util.Date;

public class DetailProdukKualitasStandarDTO {

    private int delFlag;
    private Double hargaProduk;
    private String deskripsi;
    private Date tanggal;
    private int stokProduk;
    private Long id_produk_kualitas_standar;
    private Long Id_kategori_produk;

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
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

    public Long getId_produk_kualitas_standar() {
        return id_produk_kualitas_standar;
    }

    public void setId_produk_kualitas_standar(Long id_produk_kualitas_standar) {
        this.id_produk_kualitas_standar = id_produk_kualitas_standar;
    }

    public Long getId_kategori_produk() {
        return Id_kategori_produk;
    }

    public void setId_kategori_produk(Long id_kategori_produk) {
        Id_kategori_produk = id_kategori_produk;
    }
}
