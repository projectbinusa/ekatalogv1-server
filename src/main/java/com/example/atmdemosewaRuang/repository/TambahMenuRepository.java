package com.example.atmdemosewaRuang.repository;

import com.example.atmdemosewaRuang.model.TambahMenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TambahMenuRepository extends JpaRepository<TambahMenuModel, Long> {
}
