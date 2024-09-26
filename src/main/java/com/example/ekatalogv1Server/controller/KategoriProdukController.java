package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.KategoriProdukDTO;
import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.KategoriProduk;
import com.example.ekatalogv1Server.service.admin.KategoriProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/kategori_produk")
@CrossOrigin(origins = "*")
public class KategoriProdukController {

    @Autowired
    private KategoriProdukService kategoriProdukService;

    // Get All data
    @GetMapping
    public CommonResponse<List<KategoriProduk>> getAll() {
        return ResponseHelper.ok(kategoriProdukService.getAll());
    }

    // Get byId data
    @GetMapping("/{id}")
    public CommonResponse<KategoriProduk> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(kategoriProdukService.getById(id));
    }

    // Add data
    @PostMapping("/add")
    public CommonResponse<KategoriProduk> add(@RequestBody KategoriProdukDTO kategoriProdukDTO) {
        return ResponseHelper.ok(kategoriProdukService.add(kategoriProdukDTO));
    }
}
