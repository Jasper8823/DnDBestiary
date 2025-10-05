package com.example.DnDProject.Repositories.Login;

import com.example.DnDProject.Entities.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {
    Optional<Login> findByEmail(String email);
}

