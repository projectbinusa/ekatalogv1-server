package com.example.atmdemosewaRuang.service;

import com.example.atmdemosewaRuang.model.TambahMenuModel;
import com.example.atmdemosewaRuang.repository.TambahMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TambahMenuService {

    private TambahMenuRepository tambahMenuRepository;

    @Autowired
    public TambahMenuService(TambahMenuRepository tambahMenuRepository) {
        this.tambahMenuRepository = tambahMenuRepository;
    }

    // getAllData
    public List<TambahMenuModel> getAllData() {
        return tambahMenuRepository.findAll();
    }

    // getById
    public Optional<TambahMenuModel> getById(Long id) {
        return tambahMenuRepository.findById(id);
    }

    // tambah data
    public TambahMenuModel createData(TambahMenuModel tambahMenuModel) {
        return tambahMenuRepository.save(tambahMenuModel);
    }

    // ubah data
    public TambahMenuModel updateData(Long id, TambahMenuModel updateMenuTambah) {
        Optional<TambahMenuModel> existing = tambahMenuRepository.findById(id);
        if (existing.isPresent()) {
            TambahMenuModel existingMenuTambah = existing.get();
            existingMenuTambah.setJenis(updateMenuTambah.getJenis());
            existingMenuTambah.setNama_item(updateMenuTambah.getNama_item());
            existingMenuTambah.setSatuan_peminjaman(updateMenuTambah.getSatuan_peminjaman());
            return tambahMenuRepository.save(existingMenuTambah);
        } else {
            throw new IllegalArgumentException("Menu tambahan dengan Id = " + id + " tidak ditemukan");
        }
    }

    // delete data
    public void deleteDataManuTambah(Long id) {
        Optional<TambahMenuModel> hapusMenuTambah = tambahMenuRepository.findById(id);
        if (hapusMenuTambah.isPresent()) {
            tambahMenuRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Menu tambahan dengan Id = " + id + " tidak ditemukan");
        }
    }
}