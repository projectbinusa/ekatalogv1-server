package com.example.ekatalogv1Server.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProdukKualitasTinggiDTO {

    private String namaProduk;
    private String cashKredit;
    private String status;
    private String layanan;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tanggal;
    private String jenisProyek;
    private Long idDetailProdukTinggi;
    private Long IdKategoriProduk;
    private int delFlag;

    // Getter and Setter

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

    public Long getIdDetailProdukTinggi() {
        return idDetailProdukTinggi;
    }

    public void setIdDetailProdukTinggi(Long idDetailProdukTinggi) {
        this.idDetailProdukTinggi = idDetailProdukTinggi;
    }

    public Long getIdKategoriProduk() {
        return IdKategoriProduk;
    }

    public void setIdKategoriProduk(Long idKategoriProduk) {
        IdKategoriProduk = idKategoriProduk;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
