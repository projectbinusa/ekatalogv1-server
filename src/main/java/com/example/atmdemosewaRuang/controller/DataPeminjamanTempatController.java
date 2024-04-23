package com.example.atmdemosewaRuang.controller;

import com.example.atmdemosewaRuang.model.DataPeminjamanTempatModel;
import com.example.atmdemosewaRuang.model.PelangganModel;
import com.example.atmdemosewaRuang.model.TambahMenuModel;
import com.example.atmdemosewaRuang.service.DataPeminjamanTempatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/data_tempat")
@CrossOrigin(origins = "*")
public class DataPeminjamanTempatController {

    @Autowired
    private DataPeminjamanTempatService dataPeminjamanTempatService;

    // GetAllData
    @GetMapping("/all")
    public ResponseEntity<List<DataPeminjamanTempatModel>> getAllData() {
        List<DataPeminjamanTempatModel> dataPeminjamanTempatModels = dataPeminjamanTempatService.getAllData();
        return new ResponseEntity<>(dataPeminjamanTempatModels, HttpStatus.OK);
    }

    // GetById
    @GetMapping("/peminjaman_tempat/{id}")
    public ResponseEntity<DataPeminjamanTempatModel> getById(@PathVariable Long id) {
        Optional<DataPeminjamanTempatModel> dataPeminjamanTempatModels = dataPeminjamanTempatService.getById(id);
        return dataPeminjamanTempatModels.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add data
    @PostMapping("/add")
    public ResponseEntity<DataPeminjamanTempatModel> createData(@RequestBody DataPeminjamanTempatModel dataPeminjamanTempatModel) {
        DataPeminjamanTempatModel newData = dataPeminjamanTempatService.createData(dataPeminjamanTempatModel);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    // Update data
    @PutMapping("/{id}")
    public ResponseEntity<DataPeminjamanTempatModel> updateData(@PathVariable Long id, @RequestBody DataPeminjamanTempatModel ubahTempatPeminjaman) {
        DataPeminjamanTempatModel putTempatPeminjaman = dataPeminjamanTempatService.updateData(id, ubahTempatPeminjaman);
        return new ResponseEntity<>(putTempatPeminjaman, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        dataPeminjamanTempatService.deleteDataPeminjamanTempat (id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
