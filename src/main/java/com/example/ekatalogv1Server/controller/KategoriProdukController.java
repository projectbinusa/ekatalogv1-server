package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.KategoriProdukDTO;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.service.admin.KategoriProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/kategori_produk")
@CrossOrigin(origins = "*")
public class KategoriProdukController {

    @Autowired
    private KategoriProdukService kategoriProdukService;

    @GetMapping
    public ResponseEntity<List<KategoriProduk>> getAll() {
        List<KategoriProduk> kategoriProduks = kategoriProdukService.getAll();
        return new ResponseEntity<>(kategoriProduks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategoriProduk> getById(@PathVariable Long id) {
        Optional<KategoriProduk> kategoriProduk = kategoriProdukService.getById(id);
        return kategoriProduk.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "kategori produk not found"));
    }

    @PostMapping("/add")
    public ResponseEntity<KategoriProduk> add(@RequestBody KategoriProdukDTO kategoriProdukDTO) {
        KategoriProduk newData = kategoriProdukService.add(kategoriProdukDTO);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }
}
