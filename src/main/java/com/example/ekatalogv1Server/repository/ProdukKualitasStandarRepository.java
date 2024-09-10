package com.example.ekatalogv1Server.repository;

import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdukKualitasStandarRepository extends JpaRepository<ProdukKualitasStandar , Long> {

    @Query("SELECT p FROM ProdukKualitasStandar p WHERE p.kategoriProduk.namaKategori = :namaKategori")
    List<ProdukKualitasStandar> findBarangByKategoriNama(@Param("namaKategori") String namaKategori);
}
