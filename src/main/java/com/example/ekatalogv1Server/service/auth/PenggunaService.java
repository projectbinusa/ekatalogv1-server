package com.example.ekatalogv1Server.service.auth;

import com.example.ekatalogv1Server.dto.LoginRequest;
import com.example.ekatalogv1Server.dto.PenggunaDTO;
import com.example.ekatalogv1Server.exception.BadRequestException;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import com.example.ekatalogv1Server.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public Pengguna addPengguna(PenggunaDTO user) {
        Pengguna pengguna = new Pengguna();
        if (penggunaRepository.findByUsername(user.getUsernamePengguna()).isPresent()) {
            throw new BadRequestException("Username pengguna sudah digunakan");
        }
        String userPass = user.getPasswordPengguna().trim();
        boolean PasswordIsNotValid = !userPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}");
        if (PasswordIsNotValid) throw new BadRequestException("Password not valid!");
        String encodedPassword = encoder.encode(user.getPasswordPengguna());
        pengguna.setPasswordPengguna(encodedPassword);
        pengguna.setNamaPengguna(user.getNamaPengguna());
        pengguna.setUsernamePengguna(user.getUsernamePengguna());
        pengguna.setRolePengguna(user.getRolePengguna());
        pengguna.setDelFlag(1);
        pengguna.setTanggal(new Date());
        return penggunaRepository.save(pengguna);
    }

    public Map<Object, Object> login(LoginRequest loginRequest) {
        Optional<Pengguna> userOptional = penggunaRepository.findByUsername(loginRequest.getUsername());
        Pengguna user = userOptional.orElseThrow(() -> new NotFoundException("Username not found"));
        if (encoder.matches(loginRequest.getPassword(), user.getPasswordPengguna())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken(authentication);
            penggunaRepository.save(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<Object, Object> response = new HashMap<>();
            response.put("data", user);
            response.put("token", jwt);
            response.put("type_token", user.getRolePengguna());
            return response;
        }
        throw new NotFoundException("Password not found");
    }

    public List<Pengguna> getAll() {
        return penggunaRepository.findAll();
    }

    public Pengguna getById(Long id) {
        return penggunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengguna dengan ID " + id + " tidak ditemukan"));
    }

    public Pengguna put(Long id , Pengguna user) {
        Pengguna update = penggunaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id tidak ditemukan"));
        String encodedPassword = encoder.encode(user.getPasswordPengguna());
        update.setNamaPengguna(user.getNamaPengguna());
        update.setRolePengguna(user.getRolePengguna());
        update.setPasswordPengguna(encodedPassword);
        return penggunaRepository.save(update);
    }

    public boolean delete(Long id) {
        if (penggunaRepository.existsById(id)) {
            penggunaRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Pungguna dengan Id " + id + " not found");
        }
    }
}
