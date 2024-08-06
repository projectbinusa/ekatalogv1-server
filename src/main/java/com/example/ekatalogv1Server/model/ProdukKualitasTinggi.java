package com.example.ekatalogv1Server.model;

import com.example.ekatalogv1Server.auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "produk_kualitas_tinggi")
public class ProdukKualitasTinggi extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk_kualitas_tinggi")
    private Long id;

    @Column(name = "del_flag", nullable = false, columnDefinition = "int default 1")
    private int delFlag;

    @Column(name = "nama_produk", nullable = false, length = 255)
    private String namaProduk;

    @Column(name = "cash_kredit", nullable = false, length = 255)
    private String cashKredit;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @Column(name = "layanan", nullable = false, length = 255)
    private String layanan;

    @Column(name = "jenis_proyek", nullable = false, length = 255)
    private String jenisProyek;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "Id_kategori_produk", updatable = false)
    private KategoriProduk kategoriProduk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

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

    public KategoriProduk getKategoriProduk() {
        return kategoriProduk;
    }

    public void setKategoriProduk(KategoriProduk kategoriProduk) {
        this.kategoriProduk = kategoriProduk;
    }
}
