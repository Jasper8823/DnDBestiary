package com.example.DnDProject.Repositories.Login;

import com.example.DnDProject.Entities.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
}
