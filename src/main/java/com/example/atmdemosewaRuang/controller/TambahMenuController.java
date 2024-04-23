package com.example.atmdemosewaRuang.controller;

import com.example.atmdemosewaRuang.model.TambahMenuModel;
import com.example.atmdemosewaRuang.service.TambahMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu_tambahan")
@CrossOrigin(origins = "*")
public class TambahMenuController {

    @Autowired
    private TambahMenuService tambahMenuService;

    // GetAllData
    @GetMapping("/all")
    public ResponseEntity<List<TambahMenuModel>> getAllData() {
        List<TambahMenuModel> tambahMenuModels = tambahMenuService.getAllData();
        return new ResponseEntity<>(tambahMenuModels, HttpStatus.OK);
    }

    // GetById
    @GetMapping("/menu_tambahan/{id}")
    public ResponseEntity<TambahMenuModel> getById(@PathVariable Long id) {
        Optional<TambahMenuModel> tambahMenuModels = tambahMenuService.getById(id);
        return tambahMenuModels.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add data
    @PostMapping("/add")
    public ResponseEntity<TambahMenuModel> createData(@RequestBody TambahMenuModel tambahMenuModel) {
        TambahMenuModel newData = tambahMenuService.createData(tambahMenuModel);
        return new ResponseEntity<>(newData, HttpStatus.OK);
    }

    // Update data
    @PutMapping("/{id}")
    public ResponseEntity<TambahMenuModel> updateData(@PathVariable Long id, @RequestBody TambahMenuModel ubahMenuTambah) {
        TambahMenuModel putMenuTambah = tambahMenuService.updateData(id, ubahMenuTambah);
        return new ResponseEntity<>(putMenuTambah, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        tambahMenuService.deleteDataManuTambah(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}