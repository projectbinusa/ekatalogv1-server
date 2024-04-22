package com.example.atmdemosewaRuang.model;

import javax.persistence.*;

@Entity
@Table(name = "data_ruang")
public class DataRuangModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_ruangan")
    private String nomor_ruangan;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "tempat")
    private String tempat;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomor_ruangan() {
        return nomor_ruangan;
    }
    public void setNomor_ruangan(String nomor_ruangan) {
        this.nomor_ruangan = nomor_ruangan;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTempat() {
        return tempat;
    }
    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
