package com.example.atmdemosewaRuang.model;

import javax.persistence.*;

@Entity
@Table(name = "menu_tambah")
public class TambahMenuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_item")
    private String nama_item;

    @Column(name = "jenis")
    private String jenis;

    @Column(name = "satuan_peminjaman")
    private String satuan_peminjaman;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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
