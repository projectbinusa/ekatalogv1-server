package com.example.atmdemosewaRuang.repository;

import com.example.atmdemosewaRuang.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    UserModel findById(long userId);
}
