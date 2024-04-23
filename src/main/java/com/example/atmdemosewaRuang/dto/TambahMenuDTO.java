package com.example.atmdemosewaRuang.dto;

public class TambahMenuDTO {

    private String nama_item;
    private String jenis;
    private String satuan_peminjaman;

    public String getNama_item() {
        return nama_item;
    }
    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getSatuan_peminjaman() {
        return satuan_peminjaman;
    }
    public void setSatuan_peminjaman(String satuan_peminjaman) {
        this.satuan_peminjaman = satuan_peminjaman;
    }
}
