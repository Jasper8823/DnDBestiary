package com.example.DnDProject.Repositories;

import com.example.DnDProject.Entities.Monster.MonsterAttributes.Danger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangerRepository extends JpaRepository<Danger,String> {
}
