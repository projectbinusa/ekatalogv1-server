
package com.example.ekatalogv1Server.service.auth;

import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PenggunaRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private PasswordEncoder encoder;

    // login atau authentication pengguna
    public Pengguna save(PenggunaDTO user) {
        try {
            System.out.println("Checking username: " + user.getUsernamePengguna());
            if (user.getUsernamePengguna() != null && user.getPasswordPengguna() != null && user.getPasswordPengguna().length() >= 8) {
                Optional<Pengguna> existingUser = userDao.findByUsername(user.getUsernamePengguna());
                System.out.println("Existing user: " + existingUser.isPresent());
                if (existingUser.isEmpty()) {
                    Pengguna newUser = new Pengguna();
                    newUser.setUsernamePengguna(user.getUsernamePengguna());
                    newUser.setPasswordPengguna(bcryptEncoder.encode(user.getPasswordPengguna()));
                    newUser.setRolePengguna(user.getRolePengguna());
                    newUser.setNamaPengguna(user.getNamaPengguna());
                    newUser.setTanggal(new Date());
                    newUser.setDelFlag(user.getDelFlag());
                    return userDao.save(newUser);
                } else {
                    throw new IllegalArgumentException("username has been used");
                }
            } else {
                throw new IllegalArgumentException("Password must be more than 8 characters");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Registration Failed: " + e.getMessage());
        }
    }


    @Override
    public UserDetails loadUserByUsername(String usernamePengguna) throws UsernameNotFoundException {
        Optional<Pengguna> optionalUser = userDao.findByUsername(usernamePengguna);
        if (optionalUser.isPresent()) {
            Pengguna user = optionalUser.get();
            List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRolePengguna().toUpperCase()));
            return new User(usernamePengguna, user.getPasswordPengguna(), roles);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + usernamePengguna);
        }
    }

    // ubah pengguna
    public Pengguna put(PenggunaUbahDTO penggunaUbahDTO, Long id) {
        Pengguna pengguna = userDao.findById(id).orElseThrow(() -> new RuntimeException("pengguna not found"));
        pengguna.setNamaPengguna(penggunaUbahDTO.getNamaPengguna());
        pengguna.setUsernamePengguna(penggunaUbahDTO.getUsernamePengguna());

        return userDao.save(pengguna);
    }

    // delete pengguna
    public boolean delete(Long id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Pungguna dengan Id " + id + " not found");
        }
    }

    public List<Pengguna> getAll() {
        return userDao.findAll();
    }

    // getById pengguna
    public Pengguna getById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengguna dengan ID " + id + " tidak ditemukan"));
    }

    public String updatePassword(Long id, PasswordChangeRequestDTO passwordChangeRequestDTO) {
        Pengguna pengguna = userDao.findById(id).orElseThrow(() -> new RuntimeException("Penggguna tidak ditemukan"));

        if (!bcryptEncoder.matches(passwordChangeRequestDTO.getPasswordLama(), pengguna.getPasswordPengguna())) {
            return "Password lama tidak sesuai";
        }
        if (!passwordChangeRequestDTO.getPasswordBaru().equals(passwordChangeRequestDTO.getKonfirmasiPassword())) {
            return "Konfirmasi password tidak cocok";
        }
        pengguna.setPasswordPengguna(bcryptEncoder.encode(passwordChangeRequestDTO.getPasswordBaru()));
        userDao.save(pengguna);

        return "Password berhasil diubah";
    }
}