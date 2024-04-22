package com.example.atmdemosewaRuang.service;

import com.example.atmdemosewaRuang.model.PelangganModel;
import com.example.atmdemosewaRuang.repository.PelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;
    public List<PelangganModel> getAllData() {
        return pelangganRepository.findAll();
    }

    // GetById
    public Optional <PelangganModel> getById(Long id) {
        return pelangganRepository.findById(id);
    }

    // Create data
    public PelangganModel createData(PelangganModel pelangganModel) {
        return pelangganRepository.save(pelangganModel);
    }

    // Tambah data
    public PelangganModel updateData(Long id, PelangganModel updatePelanggan) {
        Optional<PelangganModel> exiting = pelangganRepository.findById(id);
        if (exiting.isPresent()) {
            PelangganModel exitingPelanggan = exiting.get();
            exitingPelanggan.setNama_pelanggan(updatePelanggan.getNama_pelanggan());
            exitingPelanggan.setNo_telepon(updatePelanggan.getNo_telepon());
            return pelangganRepository.save(exitingPelanggan);
        } else {
            throw new IllegalArgumentException("Id Pelanggan" + "tidak ditemukan");
        }
    }

    // Delete
    public void deleteDataPelanggan(Long id) {
        Optional<PelangganModel> hapusPelanggan = pelangganRepository.findById(id);
        if (hapusPelanggan.isPresent()) {
            pelangganRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pelanggan dengan Id = " + id + " ,tidak ditemukan!!!");
        }
    }

}
