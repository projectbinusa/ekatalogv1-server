package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.KategoriProdukDTO;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.repository.KategoriProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KategoriProdukService {

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    public List<KategoriProduk> getAll() {
        return kategoriProdukRepository.findAll();
    }

    public Optional <KategoriProduk> getById(Long id) {
        return kategoriProdukRepository.findById(id);
    }

    public KategoriProduk add(KategoriProdukDTO kategoriProdukDTO) {
        KategoriProduk kategoriProduk = new KategoriProduk();
        kategoriProduk.setNamaKategori(kategoriProdukDTO.getNamaKategori());
        kategoriProduk.setTanggal(kategoriProdukDTO.getTanggal());

        return kategoriProdukRepository.save(kategoriProduk);
    }
}
