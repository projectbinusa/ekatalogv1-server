package com.example.ekatalogv1Server.repository;

import com.example.ekatalogv1Server.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PenggunaRepository extends JpaRepository<Pengguna , Long> {

    @Query(value = "SELECT * FROM tabel_pengguna WHERE username_pengguna = :usernamePengguna", nativeQuery = true)
    Optional<Pengguna> findByUsername(String usernamePengguna);
}
