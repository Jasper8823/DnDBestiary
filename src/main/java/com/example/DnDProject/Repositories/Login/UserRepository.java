package com.example.DnDProject.Repositories.Login;

import com.example.DnDProject.Entities.Login.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
