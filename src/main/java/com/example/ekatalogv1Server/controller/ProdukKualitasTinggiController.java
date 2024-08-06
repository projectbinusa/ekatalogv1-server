package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasTinggiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/kualitas_tinggi")
@CrossOrigin(origins = "*")
public class ProdukKualitasTinggiController {

    @Autowired
    private ProdukKualitasTinggiService produkKualitasTinggiService;

    @GetMapping
    public ResponseEntity<List<ProdukKualitasTinggi>> getAll() {
        List<ProdukKualitasTinggi> produkKualitasTinggis = produkKualitasTinggiService.getAll();
        return new ResponseEntity<>(produkKualitasTinggis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdukKualitasTinggi> getById(@PathVariable Long id) {
        Optional<ProdukKualitasTinggi> produkKualitasTinggis = produkKualitasTinggiService.getById(id);
        return produkKualitasTinggis.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<ProdukKualitasTinggi> add(@RequestBody ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        ProdukKualitasTinggi newData = produkKualitasTinggiService.add(produkKualitasTinggiDTO);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdukKualitasTinggi> updateProduk(@RequestBody ProdukKualitasTinggiDTO produkKualitasTinggiDTO, @PathVariable Long id) {
        ProdukKualitasTinggi updatedProduk = produkKualitasTinggiService.put(produkKualitasTinggiDTO, id);
        return ResponseEntity.ok(updatedProduk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produkKualitasTinggiService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
