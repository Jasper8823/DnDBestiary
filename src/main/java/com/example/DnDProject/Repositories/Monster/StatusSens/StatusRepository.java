package com.example.DnDProject.Repositories.Monster.StatusSens;

import com.example.DnDProject.Entities.Monster.Status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,String> {
}
