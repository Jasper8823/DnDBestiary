package com.example.DnDProject.Repositories.Spell;

import com.example.DnDProject.Entities.Spell.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell,String> {
}
