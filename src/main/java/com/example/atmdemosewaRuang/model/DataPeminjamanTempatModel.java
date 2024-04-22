package com.example.atmdemosewaRuang.model;

import javax.persistence.*;

@Entity
@Table(name = "data_booking_tempat")
public class DataPeminjamanTempatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pelanggan_booking")
    private String nama_pelanggan_booking;

    @Column(name = "jumlah_orang")
    private String jumlah_orang;

    @Column(name = "tambah_paket_tempat")
    private String tambah_paket_tempat;

    @Column(name = "tanggal")
    private String tanggal;

    @Column(name = "jam_awal")
    private String jam_awal;

    @Column(name = "jam_akhir")
    private String jam_akhir;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_pelanggan_booking() {
        return nama_pelanggan_booking;
    }
    public void setNama_pelanggan_booking(String nama_pelanggan_booking) {
        this.nama_pelanggan_booking = nama_pelanggan_booking;
    }

    public String getJumlah_orang() {
        return jumlah_orang;
    }
    public void setJumlah_orang(String jumlah_orang) {
        this.jumlah_orang = jumlah_orang;
    }

    public String getTambah_paket_tempat() {
        return tambah_paket_tempat;
    }
    public void setTambah_paket_tempat(String tambah_paket_tempat) {
        this.tambah_paket_tempat = tambah_paket_tempat;
    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam_awal() {
        return jam_awal;
    }
    public void setJam_awal(String jam_awal) {
        this.jam_awal = jam_awal;
    }

    public String getJam_akhir() {
        return jam_akhir;
    }
    public void setJam_akhir(String jam_akhir) {
        this.jam_akhir = jam_akhir;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
