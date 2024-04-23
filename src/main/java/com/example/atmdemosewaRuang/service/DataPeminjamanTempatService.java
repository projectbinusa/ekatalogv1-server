package com.example.atmdemosewaRuang.service;

import com.example.atmdemosewaRuang.model.DataPeminjamanTempatModel;
import com.example.atmdemosewaRuang.model.PelangganModel;
import com.example.atmdemosewaRuang.repository.DataPeminjamanTempatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataPeminjamanTempatService {

   @Autowired
    private DataPeminjamanTempatRepository dataPeminjamanTempatRepository;
   public List<DataPeminjamanTempatModel> getAllData() {
       return dataPeminjamanTempatRepository.findAll();
   }

    // GetById
    public Optional<DataPeminjamanTempatModel> getById(Long id) {
        return dataPeminjamanTempatRepository.findById(id);
    }

    // Tambah data
    public DataPeminjamanTempatModel createData(DataPeminjamanTempatModel dataPeminjamanTempatModel) {
        return dataPeminjamanTempatRepository.save(dataPeminjamanTempatModel);
    }

    // Ubah data
    public DataPeminjamanTempatModel updateData(Long id, DataPeminjamanTempatModel updateTempatBooking) {
        Optional<DataPeminjamanTempatModel> exiting = dataPeminjamanTempatRepository.findById(id);
        if (exiting.isPresent()) {
            DataPeminjamanTempatModel exitingTempatBooking = exiting.get();
            exitingTempatBooking.setJam_awal(updateTempatBooking.getJam_awal());
            exitingTempatBooking.setJam_akhir(updateTempatBooking.getJam_akhir());
            exitingTempatBooking.setTanggal(updateTempatBooking.getTanggal());
            exitingTempatBooking.setJumlah_orang(updateTempatBooking.getJumlah_orang());
            exitingTempatBooking.setKeterangan(updateTempatBooking.getKeterangan());
            exitingTempatBooking.setPelangganModel(updateTempatBooking.getPelangganModel());
            exitingTempatBooking.setTambahMenuModel(updateTempatBooking.getTambahMenuModel());
            exitingTempatBooking.setDataRuangModel(updateTempatBooking.getDataRuangModel());
            return dataPeminjamanTempatRepository.save(exitingTempatBooking);
        } else {
            throw new IllegalArgumentException("Id Peminjaman tempat" + "tidak ditemukan");
        }
    }

    // Delete
    public void deleteDataPeminjamanTempat(Long id) {
        Optional<DataPeminjamanTempatModel> hapusPeminjamanTempat = dataPeminjamanTempatRepository.findById(id);
        if (hapusPeminjamanTempat.isPresent()) {
            dataPeminjamanTempatRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tempat dengan Id = " + id + " ,tidak ditemukan!!!");
        }
    }

}