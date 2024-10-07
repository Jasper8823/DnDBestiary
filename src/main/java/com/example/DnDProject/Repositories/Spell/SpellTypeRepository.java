package com.example.DnDProject.Repositories.Spell;

import com.example.DnDProject.Entities.Spell.SpellType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellTypeRepository extends JpaRepository<SpellType,String> {
}
