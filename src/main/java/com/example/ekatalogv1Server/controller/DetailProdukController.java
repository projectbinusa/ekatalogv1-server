package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.service.admin.DetailProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail_produk")
@CrossOrigin(origins = "*")
public class DetailProdukController {

    @Autowired
    private DetailProdukService detailProdukService;

    // Get All data kualitas standar
    @GetMapping("/kualitas_standar")
    public CommonResponse<List<DetailProdukKualitasStandar>> getAll() {
        return ResponseHelper.ok(detailProdukService.getAll());
    }

    // Get All data kualitas tinggi
    @GetMapping("/kualitas_tinggi")
    public CommonResponse<List<DetailProdukKualitasTinggi>> getAllKualitasTinggi() {
        return ResponseHelper.ok(detailProdukService.getAllKualitasTinggi());
    }

    // Get byId kualitas standar
    @GetMapping("/kualitas_standar/{id}")
    public CommonResponse<DetailProdukKualitasStandar> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.getById(id));
    }

    // Get byId kualitas tinggi
    @GetMapping("/kualitas_tinggi/{id}")
    public CommonResponse<DetailProdukKualitasTinggi> getByIdKualitasTinggi(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.getByIdKualitasTinggi(id));
    }

    // Add data kualitas standar
    @PostMapping("/add/kualitas_standar")
    public CommonResponse<DetailProdukKualitasStandar> add(@RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        return ResponseHelper.ok(detailProdukService.add(detailProdukKualitasStandarDTO));
    }

    // Add data kualitas tinggi
    @PostMapping("/add/kualitas_tinggi")
    public CommonResponse<DetailProdukKualitasTinggi> addKualitasTinggi(@RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        return ResponseHelper.ok(detailProdukService.addKualitasTinggi(detailProdukKualitasTinggiDTO));
    }

    // Put data kualitas standar
    @PutMapping("/kualitas_standar/{id}")
    public CommonResponse<DetailProdukKualitasStandar> updateProduk(@PathVariable("id") Long id , @RequestBody DetailProdukKualitasStandarDTO detailProdukKualitasStandarDTO) {
        return ResponseHelper.ok(detailProdukService.put(detailProdukKualitasStandarDTO , id));
    }

    // Put data kualitas tinggi
    @PutMapping("/kualitas_tinggi/{id}")
    public CommonResponse<DetailProdukKualitasTinggi> updateProdukKualitasTinggi(@PathVariable("id") Long id , @RequestBody DetailProdukKualitasTinggiDTO detailProdukKualitasTinggiDTO) {
        return ResponseHelper.ok(detailProdukService.putKualitasTinggi(detailProdukKualitasTinggiDTO , id));
    }

    // Delete kualitas standar
    @DeleteMapping("/kualitas_standar/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.deleteKualitasStandar(id));
    }

    // Delete kualiatas tinggi
    @DeleteMapping("/kualitas_tinggi/{id}")
    public CommonResponse<?> deleteKualitasTinggi(@PathVariable("id") Long id) {
        return ResponseHelper.ok(detailProdukService.deleteKualitasTinggi(id));
    }
}
