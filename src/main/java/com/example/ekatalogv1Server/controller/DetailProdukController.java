package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.DetailProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.dto.DetailProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.model.DetailProdukKualitasStandar;
import com.example.ekatalogv1Server.model.DetailProdukKualitasTinggi;
import com.example.ekatalogv1Server.service.admin.DetailProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detail_produk")
@CrossOrigin(origins = "*")
public class DetailProdukController {

    @Autowired
    private DetailProdukService detailProdukService;

    @GetMapping("/kualitas_standar")
    public ResponseEntity<List<DetailProdukKualitasStandar>> getAll() {
        List<DetailProdukKualitasStandar> detailProduks = detailProdukService.getAll();
        return new ResponseEntity<>(detailProduks, HttpStatus.OK);
    }

    @GetMapping("/kualitas_tinggi")
    public ResponseEntity<List<DetailProdukKualitasTinggi>> getAllKualitasTinggi() {
        List<DetailProdukKualitasTinggi> detailProduk = detailProdukService.getAllKualitasTinggi();
        return new ResponseEntity<>(detailProduk, HttpStatus.OK);
    }

    @GetMapping("/kualitas_standar/{id}")
    public ResponseEntity<DetailProdukKualitasStandar> getById(@PathVariable Long id) {
        Optional<DetailProdukKualitasStandar> detailProduk = detailProdukService.getById(id);
        return detailProduk.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Produk kualitas standar Not Found"));
    }

    @GetMapping("/kualitas_tinggi/{id}")
    public ResponseEntity<DetailProdukKualitasTinggi> getByIdKualitasTinggi(@PathVariable Long id) {
        Optional<DetailProdukKualitasTinggi> detailProduk = detailProdukService.getByIdKualitasTinggi(id);
        return detailProduk.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "detail produk kualitas tinggi not found"));
    }

    @PostMapping("/add/kualitas_standar")
    public ResponseEntity<DetailProdukKualitasStandar> add(@RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        DetailProdukKualitasStandar newData = detailProdukService.add(detailProdukKualitasStandarDTO);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    @PostMapping("/add/kualitas_tinggi")
    public ResponseEntity<DetailProdukKualitasTinggi> addKualitasTinggi(@RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        DetailProdukKualitasTinggi newData = detailProdukService.addKualitasTinggi(detailProdukKualitasTinggiDTO);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    @PutMapping("/kualitas_standar/{id}")
    public ResponseEntity<DetailProdukKualitasStandar> updateProduk(@RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO, @PathVariable Long id) {
        DetailProdukKualitasStandar updateProduk = detailProdukService.put(detailProdukKualitasStandarDTO, id);
        return ResponseEntity.ok(updateProduk);
    }

    @PutMapping("/kualitas_tinggi/{id}")
    public ResponseEntity<DetailProdukKualitasTinggi> updateProdukKualitasTinggi(@RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO, @PathVariable Long id) {
        DetailProdukKualitasTinggi updateProdukKualitasTinggi = detailProdukService.putKualitasTinggi(detailProdukKualitasTinggiDTO, id);
        return ResponseEntity.ok(updateProdukKualitasTinggi);
    }

    @DeleteMapping("/kualitas_standar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detailProdukService.deleteKualitasStandar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/kualitas_tinggi/{id}")
    public ResponseEntity<Void> deleteKualitasTinggi(@PathVariable Long id) {
        detailProdukService.deleteKualitasTinggi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
