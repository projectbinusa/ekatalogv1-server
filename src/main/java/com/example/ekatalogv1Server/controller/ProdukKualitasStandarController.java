package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasStandarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/kualitas_standar")
@CrossOrigin(origins = "*")
public class ProdukKualitasStandarController {

    @Autowired
    private ProdukKualitasStandarService produkKualitasStandarService;

    @GetMapping
    public ResponseEntity<List<ProdukKualitasStandar>> getAll() {
        List<ProdukKualitasStandar> produkKualitasStandars = produkKualitasStandarService.getAll();
        return new ResponseEntity<>(produkKualitasStandars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdukKualitasStandar> getById(@PathVariable Long id) {
        Optional<ProdukKualitasStandar> produkKualitasStandar = produkKualitasStandarService.getById(id);
        return produkKualitasStandar.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produk Kualitas Standar not found"));
    }

    @PostMapping("/add")
    public ResponseEntity<ProdukKualitasStandar> add(@RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        ProdukKualitasStandar newData = produkKualitasStandarService.add(produkKualitasStandarDTO);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdukKualitasStandar> updateProduk(@RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO, @PathVariable Long id) {
        ProdukKualitasStandar updatedProduk = produkKualitasStandarService.put(produkKualitasStandarDTO, id);
        return ResponseEntity.ok(updatedProduk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produkKualitasStandarService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
