package com.example.ekatalogv1Server.dto;

import java.util.Date;

public class KategoriProdukDTO {

    private String namaKategori;
    private Date tanggal;

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
