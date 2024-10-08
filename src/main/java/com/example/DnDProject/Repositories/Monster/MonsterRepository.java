package com.example.DnDProject.Repositories.Monster;

import com.example.DnDProject.Entities.Monster.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Integer> {
}
