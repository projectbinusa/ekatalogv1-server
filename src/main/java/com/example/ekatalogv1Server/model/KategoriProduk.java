package com.example.ekatalogv1Server.model;

import com.example.ekatalogv1Server.auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "kategori_produk")
public class KategoriProduk extends DateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_kategori_produk")
    private Long id;

    @Column(name = "nama_kategori", nullable = false, length = 255)
    private String namaKategori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
