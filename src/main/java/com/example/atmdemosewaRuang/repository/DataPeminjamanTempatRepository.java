package com.example.atmdemosewaRuang.repository;

import com.example.atmdemosewaRuang.model.DataPeminjamanTempatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPeminjamanTempatRepository extends JpaRepository<DataPeminjamanTempatModel, Long> {
}
