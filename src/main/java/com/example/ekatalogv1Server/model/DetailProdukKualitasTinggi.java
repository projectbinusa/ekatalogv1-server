package com.example.ekatalogv1Server.model;

import com.example.ekatalogv1Server.auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detail_produk_kualitas_tinggi")
public class DetailProdukKualitasTinggi extends DateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_produk_tinggi")
    private Long id;

    @Column(name = "nama_produk", nullable = false, length = 255)
    private String namaProduk;

    @Column(name = "del_flag", nullable = false, columnDefinition = "int default 1")
    private int delFlag;

    @Column(name = "harga_produk", nullable = false, length = 255)
    private Double hargaProduk;

    @Column(name = "deskripsi", nullable = false, columnDefinition = "text")
    private String deskripsi;

    @Column(name = "tanggal", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Jakarta")
    private Date tanggal;

    @Column(name = "stok_produk", nullable = false, length = 255)
    private int stokProduk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

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
}
