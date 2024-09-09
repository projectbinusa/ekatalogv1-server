package com.example.ekatalogv1Server.repository;

import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdukKualitasTinggiRepository extends JpaRepository<ProdukKualitasTinggi , Long> {

    @Query("SELECT p FROM ProdukKualitasTinggi p WHERE p.kategoriProduk.namaKategori = :namaKategori")
    List<ProdukKualitasTinggi> findProdukByKategoriNama(@Param("namaKategori") String namaKategori);
}
