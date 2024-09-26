package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import com.example.ekatalogv1Server.service.auth.PenggunaService;
import com.example.ekatalogv1Server.service.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pengguna")
@CrossOrigin(origins = "*")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    // Endpoint Untuk Autentikasi Pengguna
    @PostMapping("/add")
    public CommonResponse<Pengguna> addPengguna(@RequestBody PenggunaDTO penggunaDTO) {
        return ResponseHelper.ok(penggunaService.addPengguna(penggunaDTO));
    }

    @PostMapping("/login")
    public CommonResponse<?> authenticatePengguna(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok(penggunaService.login(loginRequest));
    }

    // Update data pengguna
    @PutMapping("/{id}")
    public CommonResponse<Pengguna> update(@PathVariable("id") Long id , @RequestBody Pengguna user) {
        return ResponseHelper.ok(penggunaService.put(id, user));
    }

    // Delete data pengguna
    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(penggunaService.delete(id));
    }

    // Get all data pengguna
    @GetMapping
    public CommonResponse<List<Pengguna>> getAll() {
        return ResponseHelper.ok(penggunaService.getAll());
    }

    // Get byId data pengguna
    @GetMapping("/{id}")
    public CommonResponse<Pengguna> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(penggunaService.getById(id));
    }
}
