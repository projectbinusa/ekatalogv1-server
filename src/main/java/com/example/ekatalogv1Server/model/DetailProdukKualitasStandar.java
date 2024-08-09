package com.example.ekatalogv1Server.model;

import com.example.ekatalogv1Server.auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "detail_produk_kualitas_standar")
public class DetailProdukKualitasStandar extends DateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_produk_standar")
    private Long id;

    @Column(name = "del_flag", nullable = false, columnDefinition = "int default 1")
    private int delFlag;

    @Column(name = "harga_produk", nullable = false, length = 255)
    private Double hargaProduk;

    @Column(name = "deskripsi", nullable = false, length = 255)
    private String deskripsi;

    @Column(name = "stok_produk", nullable = false, length = 255)
    private int stokProduk;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_produk_kualitas_standar", updatable = false)
    private ProdukKualitasStandar produkKualitasStandar;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_kategori_produk", updatable = false)
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

    public ProdukKualitasStandar getProdukKualitasStandar() {
        return produkKualitasStandar;
    }

    public void setProdukKualitasStandar(ProdukKualitasStandar produkKualitasStandar) {
        this.produkKualitasStandar = produkKualitasStandar;
    }

    public KategoriProduk getKategoriProduk() {
        return kategoriProduk;
    }

    public void setKategoriProduk(KategoriProduk kategoriProduk) {
        this.kategoriProduk = kategoriProduk;
    }
}
