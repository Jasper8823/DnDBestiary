package com.example.DnDProject.Repositories.Character;

import com.example.DnDProject.Entities.Character.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character,Integer> {
}
