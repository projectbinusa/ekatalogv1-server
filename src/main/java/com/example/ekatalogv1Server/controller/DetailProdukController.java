package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.DetailProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.dto.DetailProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.exception.CommonResponse;
import com.example.ekatalogv1Server.exception.ResponseHelper;
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
    public CommonResponse<List<DetailProdukKualitasStandar>> getAll() {
        return ResponseHelper.ok(detailProdukService.getAll());
    }

    @GetMapping("/kualitas_tinggi")
    public CommonResponse<List<DetailProdukKualitasTinggi>> getAllKualitasTinggi() {
        return ResponseHelper.ok(detailProdukService.getAllKualitasTinggi());
    }

    @GetMapping("/kualitas_standar/{id}")
    public CommonResponse<DetailProdukKualitasStandar> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.getById(id));
    }

    @GetMapping("/kualitas_tinggi/{id}")
    public CommonResponse<DetailProdukKualitasTinggi> getByIdKualitasTinggi(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.getByIdKualitasTinggi(id));
    }

    @PostMapping("/add/kualitas_standar")
    public CommonResponse<DetailProdukKualitasStandar> add(@RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        return ResponseHelper.ok(detailProdukService.add(detailProdukKualitasStandarDTO));
    }

    @PostMapping("/add/kualitas_tinggi")
    public CommonResponse<DetailProdukKualitasTinggi> addKualitasTinggi(@RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        return ResponseHelper.ok(detailProdukService.addKualitasTinggi(detailProdukKualitasTinggiDTO));
    }

    @PutMapping("/kualitas_standar/{id}")
    public CommonResponse<DetailProdukKualitasStandar> updateProduk(@PathVariable("id") Long id , @RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        return ResponseHelper.ok(detailProdukService.put(detailProdukKualitasStandarDTO , id));
    }

    @PutMapping("/kualitas_tinggi/{id}")
    public CommonResponse<DetailProdukKualitasTinggi> updateProdukKualitasTinggi(@PathVariable("id") Long id , @RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        return ResponseHelper.ok(detailProdukService.putKualitasTinggi(detailProdukKualitasTinggiDTO , id));
    }

    @DeleteMapping("/kualitas_standar/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.deleteKualitasStandar(id));
    }

    @DeleteMapping("/kualitas_tinggi/{id}")
    public CommonResponse<?> deleteKualitasTinggi(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.deleteKualitasTinggi(id));
    }
}
