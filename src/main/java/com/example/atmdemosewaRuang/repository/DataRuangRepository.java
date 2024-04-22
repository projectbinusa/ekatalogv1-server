package com.example.atmdemosewaRuang.repository;

import com.example.atmdemosewaRuang.model.DataRuangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRuangRepository extends JpaRepository<DataRuangModel, Long> {
}
