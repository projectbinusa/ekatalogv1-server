package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.PaginationResponse;
import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasStandarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(path = "/pagination")
    public ResponseEntity<PaginationResponse<Page<ProdukKualitasStandar>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProdukKualitasStandar> produkKualitasStandars = produkKualitasStandarService.getAll(pageable);

        PaginationResponse<Page<ProdukKualitasStandar>> response = new PaginationResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(produkKualitasStandars);
        response.setMessage("Data fetched successfully");
        response.setPagination(Map.of(
                "totalElements", produkKualitasStandars.getTotalElements(),
                "totalPages", produkKualitasStandars.getTotalPages(),
                "currentPage", produkKualitasStandars.getNumber(),
                "pageSize", produkKualitasStandars.getSize()
        ));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
