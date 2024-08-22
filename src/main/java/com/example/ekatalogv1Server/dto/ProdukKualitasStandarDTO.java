package com.example.ekatalogv1Server.dto;

import java.util.Date;

public class ProdukKualitasStandarDTO {

    private String namaProduk;
    private String cashKredit;
    private String status;
    private String layanan;
    private Date tanggal;
    private String jenisProyek;
    private Long idDetailProdukStandar;
    private Long idKategoriProduk;
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

    public Long getIdDetailProdukStandar() {
        return idDetailProdukStandar;
    }

    public void setIdDetailProdukStandar(Long idDetailProdukStandar) {
        this.idDetailProdukStandar = idDetailProdukStandar;
    }

    public Long getIdKategoriProduk() {
        return idKategoriProduk;
    }

    public void setIdKategoriProduk(Long idKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
