package com.example.atmdemosewaRuang.controller;

import com.example.atmdemosewaRuang.model.PelangganModel;
import com.example.atmdemosewaRuang.service.PelangganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/pelanggan")
@CrossOrigin(origins = "*")
public class PelangganController {

    @Autowired
    private PelangganService pelangganService;

    // GetAllData
    @GetMapping("/all")
    public ResponseEntity<List<PelangganModel>> getAllData() {
        List<PelangganModel> pelangganModels = pelangganService.getAllData();
        return new ResponseEntity<>(pelangganModels, HttpStatus.OK);
    }

    // GetById
    @GetMapping("/pelanggan/{id}")
    public ResponseEntity<PelangganModel> getById(@PathVariable Long id) {
        Optional<PelangganModel> pelangganModels = pelangganService.getById(id);
        return pelangganModels.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add data
    @PostMapping("/add")
    public ResponseEntity<PelangganModel> createData(@RequestBody PelangganModel pelangganModel) {
        PelangganModel newData = pelangganService.createData(pelangganModel);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    // Update data
    @PutMapping("/{id}")
    public ResponseEntity<PelangganModel> updateData(@PathVariable Long id, @RequestBody PelangganModel ubahPelanggan) {
        PelangganModel putPelanggan = pelangganService.updateData(id, ubahPelanggan);
        return new ResponseEntity<>(putPelanggan, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        pelangganService.deleteDataPelanggan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
