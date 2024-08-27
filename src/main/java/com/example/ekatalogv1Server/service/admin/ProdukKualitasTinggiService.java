package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.DetailProdukKualitasTinggi;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import com.example.ekatalogv1Server.repository.DetailProdukKualitasTinggiRepository;
import com.example.ekatalogv1Server.repository.KategoriProdukRepository;
import com.example.ekatalogv1Server.repository.ProdukKualitasTinggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProdukKualitasTinggiService {

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasTinggiRepository detailProdukKualitasTinggiRepository;

    public List<ProdukKualitasTinggi> getAll() {
        return produkKualitasTinggiRepository.findAll();
    }

    public ProdukKualitasTinggi getById(Long id) {
        return produkKualitasTinggiRepository.findById(id).orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));
    }

    public ProdukKualitasTinggi add(ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        ProdukKualitasTinggi produkKualitasTinggi = new ProdukKualitasTinggi();
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(1);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasTinggiDTO.getIdKategoriProduk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasTinggi.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(produkKualitasTinggiDTO.getIdDetailProdukTinggi())
                .orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasTinggi.setDetailProdukKualitasTinggi(detailProdukKualitasTinggi);

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
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(1);

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    public String delete(Long id) {
        ProdukKualitasTinggi produk = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasTinggiRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    public Page<ProdukKualitasTinggi> getAll(Pageable pageable) {
        return produkKualitasTinggiRepository.findAll(pageable);
    }
}
