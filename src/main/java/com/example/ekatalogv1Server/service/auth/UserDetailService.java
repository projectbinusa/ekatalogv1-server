package com.example.ekatalogv1Server.service.auth;

import com.example.ekatalogv1Server.dto.PenggunaDTO;
import com.example.ekatalogv1Server.dto.PenggunaUbahDTO;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PenggunaRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    PasswordEncoder encoder;

    public Pengguna save(PenggunaDTO user) {
        try {
            if (user.getUsernamePengguna() !=null && user.getPasswordPengguna() !=null && user.getPasswordPengguna().length() >=8) {
                if (userDao.findByUsername(user.getUsernamePengguna()) == null) {
                    Pengguna newUser = new Pengguna();
                    newUser.setUsernamePengguna(user.getUsernamePengguna());
                    newUser.setPasswordPengguna(bcryptEncoder.encode(user.getPasswordPengguna()));
                    newUser.setRolePengguna(user.getRolePengguna());
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

    public Pengguna put(PenggunaUbahDTO penggunaUbahDTO, Long id) {
        Pengguna pengguna = userDao.findById(id).orElseThrow(() -> new RuntimeException("pengguna not found"));
        pengguna.setNamaPengguna(penggunaUbahDTO.getNama());
        if (penggunaUbahDTO.getPassword() != null && !penggunaUbahDTO.getPassword().isEmpty()) {
            String encodedPassword = encoder.encode(penggunaUbahDTO.getPassword());
            pengguna.setPasswordPengguna(encodedPassword);
        }

        return userDao.save(pengguna);
    }

    public void delete(Long id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
        } else {
            throw new IllegalArgumentException("id = " + id + " tidak ditemukan");
        }
    }
}
