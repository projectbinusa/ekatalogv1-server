package com.example.atmdemosewaRuang.repository;

import com.example.atmdemosewaRuang.model.PelangganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelangganRepository extends JpaRepository<PelangganModel, Long> {
}
