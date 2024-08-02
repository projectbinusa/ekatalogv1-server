package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.config.JwtTokenUtil;
import com.example.ekatalogv1Server.dto.JwtRequest;
import com.example.ekatalogv1Server.dto.JwtResponse;
import com.example.ekatalogv1Server.dto.PenggunaDTO;
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

import java.util.Collections;
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
    @PostMapping("/login")
    public ResponseEntity<?> authenticationPengguna(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        Optional<Pengguna> optionalUser = userDao.findByUsername(authenticationRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with username: " + authenticationRequest.getUsername());
        }
        Pengguna user = optionalUser.get();

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    // Endpoint untuk register
//    @PostMapping("/register")
//    public ResponseEntity<?> addPengguna(@RequestBody PenggunaDTO penggunaDTO) {
//        try {
//            Pengguna newUser = userDetailService.save(penggunaDTO);
//            return ResponseEntity.ok("User registered successfully");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", "Registration failed: " + e.getMessage()));
//        }
//    }
}
