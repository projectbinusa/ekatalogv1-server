package com.example.atmdemosewaRuang.dto;

import com.example.atmdemosewaRuang.model.DataRuangModel;
import com.example.atmdemosewaRuang.model.PelangganModel;
import com.example.atmdemosewaRuang.model.TambahMenuModel;

public class DataPeminjamanTempatDTO {

    private String jumlah_orang;
    private String tanggal;
    private String jam_awal;
    private String jam_akhir;
    private String keterangan;
    private PelangganModel pelangganModel;
    private TambahMenuModel tambahMenuModel;
    private DataRuangModel dataRuangModel;

    public String getJumlah_orang() {
        return jumlah_orang;
    }
    public void setJumlah_orang(String jumlah_orang) {
        this.jumlah_orang = jumlah_orang;
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

    public PelangganModel getPelangganModel() {
        return pelangganModel;
    }
    public void setPelangganModel(PelangganModel pelangganModel) {
        this.pelangganModel = pelangganModel;
    }

    public TambahMenuModel getTambahMenuModel() {
        return tambahMenuModel;
    }
    public void setTambahMenuModel(TambahMenuModel tambahMenuModel) {
        this.tambahMenuModel = tambahMenuModel;
    }

    public DataRuangModel getDataRuangModel() {
        return dataRuangModel;
    }
    public void setDataRuangModel(DataRuangModel dataRuangModel) {
        this.dataRuangModel = dataRuangModel;
    }
}
