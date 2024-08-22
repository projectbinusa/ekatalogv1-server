package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.KategoriProdukDTO;
import com.example.ekatalogv1Server.exception.CommonResponse;
import com.example.ekatalogv1Server.exception.ResponseHelper;
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
    public CommonResponse<List<KategoriProduk>> getAll() {
        return ResponseHelper.ok(kategoriProdukService.getAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<KategoriProduk> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(kategoriProdukService.getById(id));
    }

    @PostMapping("/add")
    public CommonResponse<KategoriProduk> add(@RequestBody KategoriProdukDTO kategoriProdukDTO) {
        return ResponseHelper.ok(kategoriProdukService.add(kategoriProdukDTO));
    }
}
