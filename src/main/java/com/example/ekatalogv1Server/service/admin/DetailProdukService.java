package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.DetailProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.dto.DetailProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DetailProdukService {

    @Autowired
    private DetailProdukKualitasStandarRepository detailProdukKualitasStandarRepository;

    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasTinggiRepository detailProdukKualitasTinggiRepository;

    public List<DetailProdukKualitasStandar> getAll() {
        return detailProdukKualitasStandarRepository.findAll();
    }

    public List<DetailProdukKualitasTinggi> getAllKualitasTinggi() {
        return detailProdukKualitasTinggiRepository.findAll();
    }

    public Optional <DetailProdukKualitasStandar> getById(Long id) {
        return detailProdukKualitasStandarRepository.findById(id);
    }

    public Optional <DetailProdukKualitasTinggi> getByIdKualitasTinggi(Long id) {
        return detailProdukKualitasTinggiRepository.findById(id);
    }

    public DetailProdukKualitasStandar add(DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        DetailProdukKualitasStandar detailProdukKualitasStandar = new DetailProdukKualitasStandar();
        ProdukKualitasStandar produkKualitasStandar = produkKualitasStandarRepository.findById(detailProdukKualitasStandarDTO.getId_produk_kualitas_standar())
                .orElseThrow(() -> new RuntimeException("produk kualitas standar not found"));
        detailProdukKualitasStandar.setProdukKualitasStandar(produkKualitasStandar);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(detailProdukKualitasStandarDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("kategori produk not found"));
        detailProdukKualitasStandar.setKategoriProduk(kategoriProduk);
        detailProdukKualitasStandar.setHargaProduk(detailProdukKualitasStandarDTO.getHargaProduk());
        detailProdukKualitasStandar.setStokProduk(detailProdukKualitasStandarDTO.getStokProduk());
        detailProdukKualitasStandar.setDeskripsi(detailProdukKualitasStandarDTO.getDeskripsi());
        detailProdukKualitasStandar.setTanggal(detailProdukKualitasStandarDTO.getTanggal());
        detailProdukKualitasStandar.setDelFlag(detailProdukKualitasStandarDTO.getDelFlag());

        return detailProdukKualitasStandarRepository.save(detailProdukKualitasStandar);
    }

    public DetailProdukKualitasTinggi addKualitasTinggi(DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = new DetailProdukKualitasTinggi();
        ProdukKualitasTinggi produkKualitasTinggi = produkKualitasTinggiRepository.findById(detailProdukKualitasTinggiDTO.getId_produk_kualitas_tinggi())
                .orElseThrow(() -> new RuntimeException("produk kualitas tinggi not found"));
        detailProdukKualitasTinggi.setProdukKualitasTinggi(produkKualitasTinggi);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(detailProdukKualitasTinggiDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("kategori produk not found"));
        detailProdukKualitasTinggi.setKategoriProduk(kategoriProduk);
        detailProdukKualitasTinggi.setHargaProduk(detailProdukKualitasTinggiDTO.getHargaProduk());
        detailProdukKualitasTinggi.setStokProduk(detailProdukKualitasTinggiDTO.getStokProduk());
        detailProdukKualitasTinggi.setDeskripsi(detailProdukKualitasTinggiDTO.getDeskripsi());
        detailProdukKualitasTinggi.setTanggal(detailProdukKualitasTinggiDTO.getTanggal());
        detailProdukKualitasTinggi.setDelFlag(detailProdukKualitasTinggiDTO.getDelFlag());

        return detailProdukKualitasTinggiRepository.save(detailProdukKualitasTinggi);
    }

    @Transactional
    public DetailProdukKualitasStandar put(DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO, Long id) {
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("detail produk not found"));
        ProdukKualitasStandar produkKualitasStandar = produkKualitasStandarRepository.findById(detailProdukKualitasStandarDTO.getId_produk_kualitas_standar())
                .orElseThrow(() -> new RuntimeException("produk kualitas standar not found"));
        detailProdukKualitasStandar.setProdukKualitasStandar(produkKualitasStandar);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(detailProdukKualitasStandarDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("kategori produk not found"));
        detailProdukKualitasStandar.setKategoriProduk(kategoriProduk);
        detailProdukKualitasStandar.setHargaProduk(detailProdukKualitasStandarDTO.getHargaProduk());
        detailProdukKualitasStandar.setStokProduk(detailProdukKualitasStandarDTO.getStokProduk());
        detailProdukKualitasStandar.setDeskripsi(detailProdukKualitasStandarDTO.getDeskripsi());
        detailProdukKualitasStandar.setTanggal(detailProdukKualitasStandarDTO.getTanggal());
        detailProdukKualitasStandar.setDelFlag(detailProdukKualitasStandarDTO.getDelFlag());

        return detailProdukKualitasStandarRepository.save(detailProdukKualitasStandar);
    }

    @Transactional
    public DetailProdukKualitasTinggi putKualitasTinggi(DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO, Long id) {
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("detail produk not found"));
        ProdukKualitasTinggi produkKualitasTinggi = produkKualitasTinggiRepository.findById(detailProdukKualitasTinggiDTO.getId_produk_kualitas_tinggi())
                .orElseThrow(() -> new RuntimeException("produk kualitas tinggi not found"));
        detailProdukKualitasTinggi.setProdukKualitasTinggi(produkKualitasTinggi);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(detailProdukKualitasTinggiDTO.getId_kategori_produk())
                .orElseThrow(() -> new RuntimeException("kategori produk not found"));
        detailProdukKualitasTinggi.setKategoriProduk(kategoriProduk);
        detailProdukKualitasTinggi.setHargaProduk(detailProdukKualitasTinggiDTO.getHargaProduk());
        detailProdukKualitasTinggi.setStokProduk(detailProdukKualitasTinggiDTO.getStokProduk());
        detailProdukKualitasTinggi.setDeskripsi(detailProdukKualitasTinggiDTO.getDeskripsi());
        detailProdukKualitasTinggi.setTanggal(detailProdukKualitasTinggiDTO.getTanggal());
        detailProdukKualitasTinggi.setDelFlag(detailProdukKualitasTinggiDTO.getDelFlag());

        return detailProdukKualitasTinggiRepository.save(detailProdukKualitasTinggi);
    }

    public void deleteKualitasStandar(Long id) {
        if (detailProdukKualitasStandarRepository.existsById(id)) {
            detailProdukKualitasStandarRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Id = " + id + " tidak ditemukan");
        }
    }

    public void deleteKualitasTinggi(Long id) {
        if (detailProdukKualitasTinggiRepository.existsById(id)) {
            detailProdukKualitasTinggiRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("id = " + id + " tidak ditemukan");
        }
    }
}
