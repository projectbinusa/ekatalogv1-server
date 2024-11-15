package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProdukKualitasStandarService {
    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasStandarRepository detailProdukKualitasStandarRepository;

    public List<ProdukKualitasStandar> getAll() {
        return produkKualitasStandarRepository.findAll();
    }

    public ProdukKualitasStandar getById(Long id) {
        return produkKualitasStandarRepository.findById(id).orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));
    }

    // fungsi tambah data produk kualitas standar
    public ProdukKualitasStandar add(ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        ProdukKualitasStandar produkKualitasStandar = new ProdukKualitasStandar();
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setTanggal(produkKualitasStandarDTO.getTanggal());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        produkKualitasStandar.setDelFlag(1);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasStandarDTO.getIdKategoriProduk()).orElseThrow(() -> new RuntimeException("Kategori produk not found"));
        produkKualitasStandar.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(produkKualitasStandarDTO.getIdDetailProdukStandar()).orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasStandar.setDetailProdukKualitasStandar(detailProdukKualitasStandar);

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    // fungsi edit produk kualitas standar
    @Transactional
    public ProdukKualitasStandar put(ProdukKualitasStandarDTO produkKualitasStandarDTO, Long id) {
        ProdukKualitasStandar produkKualitasStandar = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk Kualitas Standar not found"));
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        produkKualitasStandar.setTanggal(produkKualitasStandarDTO.getTanggal());
        produkKualitasStandar.setDelFlag(1);

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    // fungsi delete produk kualitas standar
    public String delete(Long id) {
        ProdukKualitasStandar produk = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasStandarRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    // fungsi getAll data produk kualitas standar
    public Page<ProdukKualitasStandar> getAll(Pageable pageable) {
        return produkKualitasStandarRepository.findAll(pageable);
    }
}
