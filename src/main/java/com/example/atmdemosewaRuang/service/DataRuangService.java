package com.example.atmdemosewaRuang.service;

import com.example.atmdemosewaRuang.model.DataRuangModel;
import com.example.atmdemosewaRuang.repository.DataRuangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataRuangService {

    private final DataRuangRepository dataRuangRepository;

    @Autowired
    public DataRuangService(DataRuangRepository dataRuangRepository) {
        this.dataRuangRepository = dataRuangRepository;
    }

    // getAllData
    public List<DataRuangModel> getAllData() {
        return dataRuangRepository.findAll();
    }

    // getById
    public Optional<DataRuangModel> getById(Long id) {
        return dataRuangRepository.findById(id);
    }

    // Tambah
    public DataRuangModel createData(DataRuangModel dataRuangModel) {
        return dataRuangRepository.save(dataRuangModel);
    }

    // Ubah
    public DataRuangModel updateData(Long id, DataRuangModel updateRuang) {
        Optional<DataRuangModel> existing = dataRuangRepository.findById(id);
        if (existing.isPresent()) {
            DataRuangModel existingRuang = existing.get();
            existingRuang.setNomor_ruangan(updateRuang.getNomor_ruangan());
            existingRuang.setKeterangan(updateRuang.getKeterangan());
            existingRuang.setTempat(updateRuang.getTempat());
            return dataRuangRepository.save(existingRuang);
        } else {
            throw new IllegalArgumentException("Id Ruang " + id + " tidak ditemukan");
        }
    }

    // Delete
    public void deleteDataRuang(Long id) {
        Optional<DataRuangModel> hapusRuang = dataRuangRepository.findById(id);
        if (hapusRuang.isPresent()) {
            dataRuangRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Ruang dengan Id = " + id + " tidak ditemukan");
        }
    }
}