package com.example.ekatalogv1Server.dto;

public class ProdukKualitasTinggiDTO {

    private String namaProduk;
    private String cashKredit;
    private String status;
    private String layanan;
    private String jenisProyek;
    private Long kategoriProdukId;
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

    public String getJenisProyek() {
        return jenisProyek;
    }

    public void setJenisProyek(String jenisProyek) {
        this.jenisProyek = jenisProyek;
    }

    public Long getKategoriProdukId() {
        return kategoriProdukId;
    }

    public void setKategoriProdukId(Long kategoriProdukId) {
        this.kategoriProdukId = kategoriProdukId;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
