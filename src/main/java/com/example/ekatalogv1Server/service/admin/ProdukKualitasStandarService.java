package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.repository.KategoriProdukRepository;
import com.example.ekatalogv1Server.repository.ProdukKualitasStandarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProdukKualitasStandarService {

    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    public List<ProdukKualitasStandar> getAll() {
        return produkKualitasStandarRepository.findAll();
    }

    public Optional <ProdukKualitasStandar> getById(Long id) {
        return produkKualitasStandarRepository.findById(id);
    }

    public ProdukKualitasStandar add(ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        ProdukKualitasStandar produkKualitasStandar = new ProdukKualitasStandar();
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasStandarDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasStandar.setKategoriProduk(kategoriProduk);
        produkKualitasStandar.setDelFlag(produkKualitasStandarDTO.getDelFlag());

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    @Transactional
    public ProdukKualitasStandar put(ProdukKualitasStandarDTO produkKualitasStandarDTO, Long id) {
        ProdukKualitasStandar produkKualitasStandar = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk Kualitas Standar not found"));
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasStandarDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasStandar.setKategoriProduk(kategoriProduk);
        produkKualitasStandar.setDelFlag(produkKualitasStandarDTO.getDelFlag());

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    public void delete(Long id) {
        if (produkKualitasStandarRepository.existsById(id)) {
            produkKualitasStandarRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID = " + id + " tidak ditemukan");
        }
    }
}
