package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.*;
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

    public DetailProdukKualitasStandar getById(Long id) {
        return detailProdukKualitasStandarRepository.findById(id).orElseThrow(() -> new RuntimeException("Id tidak ditemukan"));
    }

    public DetailProdukKualitasTinggi getByIdKualitasTinggi(Long id) {
        return detailProdukKualitasTinggiRepository.findById(id).orElseThrow(() -> new RuntimeException("Id tidak ditemukan"));
    }

    public DetailProdukKualitasStandar add(DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        DetailProdukKualitasStandar detailProdukKualitasStandar = new DetailProdukKualitasStandar();
        detailProdukKualitasStandar.setHargaProduk(detailProdukKualitasStandarDTO.getHargaProduk());
        detailProdukKualitasStandar.setStokProduk(detailProdukKualitasStandarDTO.getStokProduk());
        detailProdukKualitasStandar.setDeskripsi(detailProdukKualitasStandarDTO.getDeskripsi());
        detailProdukKualitasStandar.setTanggal(detailProdukKualitasStandarDTO.getTanggal());
        detailProdukKualitasStandar.setDelFlag(1);
        detailProdukKualitasStandar.setNamaProduk(detailProdukKualitasStandarDTO.getNamaProduk());

        return detailProdukKualitasStandarRepository.save(detailProdukKualitasStandar);
    }

    public DetailProdukKualitasTinggi addKualitasTinggi(DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = new DetailProdukKualitasTinggi();
        detailProdukKualitasTinggi.setHargaProduk(detailProdukKualitasTinggiDTO.getHargaProduk());
        detailProdukKualitasTinggi.setStokProduk(detailProdukKualitasTinggiDTO.getStokProduk());
        detailProdukKualitasTinggi.setDeskripsi(detailProdukKualitasTinggiDTO.getDeskripsi());
        detailProdukKualitasTinggi.setTanggal(detailProdukKualitasTinggiDTO.getTanggal());
        detailProdukKualitasTinggi.setDelFlag(1);
        detailProdukKualitasTinggi.setNamaProduk(detailProdukKualitasTinggiDTO.getNamaProduk());

        return detailProdukKualitasTinggiRepository.save(detailProdukKualitasTinggi);
    }

    @Transactional
    public DetailProdukKualitasStandar put(DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO, Long id) {
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("detail produk not found"));
        detailProdukKualitasStandar.setHargaProduk(detailProdukKualitasStandarDTO.getHargaProduk());
        detailProdukKualitasStandar.setStokProduk(detailProdukKualitasStandarDTO.getStokProduk());
        detailProdukKualitasStandar.setDeskripsi(detailProdukKualitasStandarDTO.getDeskripsi());
        detailProdukKualitasStandar.setTanggal(detailProdukKualitasStandarDTO.getTanggal());
        detailProdukKualitasStandar.setDelFlag(1);
        detailProdukKualitasStandar.setNamaProduk(detailProdukKualitasStandarDTO.getNamaProduk());

        return detailProdukKualitasStandarRepository.save(detailProdukKualitasStandar);
    }

    @Transactional
    public DetailProdukKualitasTinggi putKualitasTinggi(DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO, Long id) {
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("detail produk not found"));
        detailProdukKualitasTinggi.setHargaProduk(detailProdukKualitasTinggiDTO.getHargaProduk());
        detailProdukKualitasTinggi.setStokProduk(detailProdukKualitasTinggiDTO.getStokProduk());
        detailProdukKualitasTinggi.setDeskripsi(detailProdukKualitasTinggiDTO.getDeskripsi());
        detailProdukKualitasTinggi.setTanggal(detailProdukKualitasTinggiDTO.getTanggal());
        detailProdukKualitasTinggi.setDelFlag(1);
        detailProdukKualitasTinggi.setNamaProduk(detailProdukKualitasTinggiDTO.getNamaProduk());

        return detailProdukKualitasTinggiRepository.save(detailProdukKualitasTinggi);
    }

    public String deleteKualitasStandar(Long id) {
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id detail " + id + " not found "));

        detailProdukKualitasStandarRepository.delete(detailProdukKualitasStandar);
        return "Id dengan " + id + " berhasil dihapus.";
    }

    public String deleteKualitasTinggi(Long id) {
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id detail " + id + " not found"));

        detailProdukKualitasTinggiRepository.delete(detailProdukKualitasTinggi);
        return "Id dengan " + id + " berhasil dihapus.";
    }
}
