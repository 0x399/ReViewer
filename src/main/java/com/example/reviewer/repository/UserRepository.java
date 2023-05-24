package com.example.reviewer.repository;

import com.example.reviewer.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
@org.springframework.transaction.annotation.Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
