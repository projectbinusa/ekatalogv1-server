package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProdukService {

    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    public List<ProdukKualitasStandar> getBarangByKategoriNama(String namaKategori) {
        return produkKualitasStandarRepository.findBarangByKategoriNama(namaKategori);
    }

    public List<ProdukKualitasTinggi> getProdukByKategoriNama(String namaKategori) {
        return produkKualitasTinggiRepository.findProdukByKategoriNama(namaKategori);
    }
}
