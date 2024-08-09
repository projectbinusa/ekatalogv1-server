package com.example.ekatalogv1Server.dto;

public class DetailProdukKualitasTinggiDTO {

    private int delFlag;
    private Double hargaProduk;
    private String deskripsi;
    private int stokProduk;
    private Long id_produk_kualitas_tinggi;
    private Long id_kategori_produk;

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

    public int getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(int stokProduk) {
        this.stokProduk = stokProduk;
    }

    public Long getId_produk_kualitas_tinggi() {
        return id_produk_kualitas_tinggi;
    }

    public void setId_produk_kualitas_tinggi(Long id_produk_kualitas_tinggi) {
        this.id_produk_kualitas_tinggi = id_produk_kualitas_tinggi;
    }

    public Long getId_kategori_produk() {
        return id_kategori_produk;
    }

    public void setId_kategori_produk(Long id_kategori_produk) {
        this.id_kategori_produk = id_kategori_produk;
    }
}
