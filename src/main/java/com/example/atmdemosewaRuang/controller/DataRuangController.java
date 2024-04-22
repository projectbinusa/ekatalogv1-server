package com.example.atmdemosewaRuang.controller;

import com.example.atmdemosewaRuang.model.DataRuangModel;
import com.example.atmdemosewaRuang.service.DataRuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/data_ruang")
@CrossOrigin(origins = "*")
public class DataRuangController {

    @Autowired
    private DataRuangService dataRuangService;
    // GetAllData
    @GetMapping("/all")
    public ResponseEntity<List<DataRuangModel>> getAllData() {
        List<DataRuangModel> dataRuangModels = dataRuangService.getAllData();
        return new ResponseEntity<>(dataRuangModels, HttpStatus.OK);
    }

    // GetById
    @GetMapping("/ruangan/{id}")
    public ResponseEntity<DataRuangModel> getById(@PathVariable Long id) {
        Optional<DataRuangModel> dataRuangModels = dataRuangService.getById(id);
        return dataRuangModels.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add data
    @PostMapping("/add")
    public ResponseEntity<DataRuangModel> createData(@RequestBody DataRuangModel dataRuangModel) {
        DataRuangModel newData = dataRuangService.createData(dataRuangModel);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    // Update data
    @PutMapping("/update/{id}")
    public ResponseEntity<DataRuangModel> updateData(@PathVariable Long id, @RequestBody DataRuangModel ubahRuangan) {
        DataRuangModel putRuang = dataRuangService.updateData(id, ubahRuangan);
        return new ResponseEntity<>(putRuang, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        dataRuangService.deleteDataRuang(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
