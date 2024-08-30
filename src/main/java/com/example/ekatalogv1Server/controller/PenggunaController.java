package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.config.JwtTokenUtil;
import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.exception.CommonResponse;
import com.example.ekatalogv1Server.exception.ResponseHelper;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import com.example.ekatalogv1Server.service.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pengguna")
@CrossOrigin(origins = "*")
public class PenggunaController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PenggunaRepository userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Endpoint Untuk Autentikasi Pengguna
    @PostMapping("/add")
    public CommonResponse<Pengguna> addPengguna(@RequestBody PenggunaDTO penggunaDTO) {
        return ResponseHelper.ok(userDetailService.save(penggunaDTO));
    }

    @PostMapping("/login")
    public CommonResponse<?> authenticationPengguna(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        Optional<Pengguna> optionalUser = userDao.findByUsername(authenticationRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with username: " + authenticationRequest.getUsername());
        }
        Pengguna user = optionalUser.get();

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseHelper.ok(new JwtResponse(token, user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PutMapping("/{id}")
    public CommonResponse<Pengguna> update(@PathVariable("id") Long id , @RequestBody PenggunaUbahDTO penggunaUbahDTO) {
        return ResponseHelper.ok(userDetailService.put(penggunaUbahDTO , id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(userDetailService.delete(id));
    }

    @GetMapping
    public CommonResponse<List<Pengguna>> getAll() {
        return ResponseHelper.ok(userDetailService.getAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<Pengguna> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(userDetailService.getById(id));
    }

}
