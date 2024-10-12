package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.config.JwtTokenUtil;
import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import com.example.ekatalogv1Server.service.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    // Endpoint Untuk Autentikasi add Pengguna
    @PostMapping("/add")
    public CommonResponse<Pengguna> addPengguna(@RequestBody PenggunaDTO penggunaDTO) {
        return ResponseHelper.ok(userDetailService.save(penggunaDTO));
    }

    // Update data pengguna
    @PutMapping("/{id}")
    public CommonResponse<Pengguna> update(@PathVariable("id") Long id , @RequestBody PenggunaUbahDTO penggunaUbahDTO) {
        return ResponseHelper.ok(userDetailService.put(penggunaUbahDTO , id));
    }

    // Delete data pengguna
    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(userDetailService.delete(id));
    }

    // Get all data pengguna
    @GetMapping
    public CommonResponse<List<Pengguna>> getAll() {
        return ResponseHelper.ok(userDetailService.getAll());
    }

    // Get byId data pengguna
    @GetMapping("/{id}")
    public CommonResponse<Pengguna> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(userDetailService.getById(id));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/upload_image/{id}")
    public CommonResponse<?> uploadImage(@PathVariable("id") Long id, @RequestPart("foto") MultipartFile file) {
        try {
            Pengguna uploadImage = userDetailService.uploadImage(id, file);
            return ResponseHelper.ok(uploadImage);
        } catch (NotFoundException e) {
            return ResponseHelper.error("User not found", HttpStatus.NOT_FOUND).getBody();
        } catch (IOException e) {
            return ResponseHelper.error("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        } catch (Exception e) {
            return ResponseHelper.error("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        }
    }

    @PutMapping("/update_image/{id}")
    public CommonResponse<?> updateImage(@PathVariable("id") Long id, @RequestPart("foto") MultipartFile file) {
        try {
            Pengguna updateImage = userDetailService.uploadImage(id, file);
            return ResponseHelper.ok(updateImage);
        } catch (NotFoundException e) {
            return ResponseHelper.error("User not found", HttpStatus.NOT_FOUND).getBody();
        } catch (IOException e) {
            return ResponseHelper.error("File update failed", HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.error("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        }
    }

    @PutMapping("/update_password")
    public CommonResponse<?> ubahPassword(@RequestBody PasswordChangeRequestDTO passwordChangeRequestDTO, Authentication authentication) {
        String username = authentication.getName();
        Optional<Pengguna> optionalUser = userDao.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return ResponseHelper.badRequest("User not found with username: " + username).getBody();
        }
        Long id = optionalUser.get().getIdPengguna();
        String result = userDetailService.updatePassword(id, passwordChangeRequestDTO);
        if (result.equals("Password berhasil diubah")) {
            return ResponseHelper.ok(result);
        } else {
            return ResponseHelper.badRequest(result).getBody();
        }
    }
}
