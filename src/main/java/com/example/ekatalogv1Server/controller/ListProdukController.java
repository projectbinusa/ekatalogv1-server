package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list_produk")
@CrossOrigin(origins = "*")
public class ListProdukController {

    @Autowired
    private ListProdukService listProdukService;

    // GetAll sesuai kategori kualitas standar
    @GetMapping("/kualitas_standar/{namaKategori}")
    public List<ProdukKualitasStandar> getBarangByKategori(@PathVariable String namaKategori) {
        return listProdukService.getBarangByKategoriNama(namaKategori);
    }

    // GetAll sesuai kategori kualitas tinggi
    @GetMapping("/kualitas_tinggi/{namaKategori}")
    public List<ProdukKualitasTinggi> getProdukByKategori(@PathVariable String namaKategori) {
        return listProdukService.getProdukByKategoriNama(namaKategori);
    }
}
