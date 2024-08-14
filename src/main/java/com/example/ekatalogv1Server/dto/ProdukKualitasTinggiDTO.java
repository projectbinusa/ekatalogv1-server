package com.example.ekatalogv1Server.dto;

import java.util.Date;

public class ProdukKualitasTinggiDTO {

    private String namaProduk;
    private String cashKredit;
    private String status;
    private String layanan;
    private Date tanggal;
    private String jenisProyek;
    private Long Id_kategori_produk;
    private int delFlag;

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getCashKredit() {
        return cashKredit;
    }

    public void setCashKredit(String cashKredit) {
        this.cashKredit = cashKredit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenisProyek() {
        return jenisProyek;
    }

    public void setJenisProyek(String jenisProyek) {
        this.jenisProyek = jenisProyek;
    }

    public Long getId_kategori_produk() {
        return Id_kategori_produk;
    }

    public void setId_kategori_produk(Long id_kategori_produk) {
        Id_kategori_produk = id_kategori_produk;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
