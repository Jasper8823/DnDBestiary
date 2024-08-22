package com.example.DnDProject.Entities.Spell;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SpellType {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
