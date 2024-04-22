package com.example.atmdemosewaRuang.model;

import javax.persistence.*;

@Entity
@Table(name = "pelanggan")
public class PelangganModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pelanggan")
    private String nama_pelanggan;

    @Column(name = "no_telepon")
    private String no_telepon;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }
    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNo_telepon() {
        return no_telepon;
    }
    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }
}
