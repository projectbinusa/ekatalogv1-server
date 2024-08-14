package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import com.example.ekatalogv1Server.repository.KategoriProdukRepository;
import com.example.ekatalogv1Server.repository.ProdukKualitasTinggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProdukKualitasTinggiService {

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    public List<ProdukKualitasTinggi> getAll() {
        return produkKualitasTinggiRepository.findAll();
    }

    public Optional <ProdukKualitasTinggi> getById(Long id) {
        return produkKualitasTinggiRepository.findById(id);
    }

    public ProdukKualitasTinggi add(ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        ProdukKualitasTinggi produkKualitasTinggi = new ProdukKualitasTinggi();
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasTinggiDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasTinggi.setKategoriProduk(kategoriProduk);
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(produkKualitasTinggiDTO.getDelFlag());

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    @Transactional
    public ProdukKualitasTinggi put(ProdukKualitasTinggiDTO produkKualitasTinggiDTO, Long id) {
        ProdukKualitasTinggi produkKualitasTinggi = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk Kualitas Tinggi not found"));
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasTinggiDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasTinggi.setKategoriProduk(kategoriProduk);
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(produkKualitasTinggiDTO.getDelFlag());

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    public void delete(Long id) {
        if (produkKualitasTinggiRepository.existsById(id)) {
            produkKualitasTinggiRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID = " + id + " tidak ditemukan");
        }
    }
}
