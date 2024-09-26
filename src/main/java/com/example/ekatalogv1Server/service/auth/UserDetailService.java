package com.example.ekatalogv1Server.service.auth;

import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Pengguna user;
        if (penggunaRepository.findByUsername(username).isPresent()) {
            user = penggunaRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));
        } else {
            throw new NotFoundException("User not found with username for email: " + username);
        }
        return UserDetail.buildUser(user);
    }
}
