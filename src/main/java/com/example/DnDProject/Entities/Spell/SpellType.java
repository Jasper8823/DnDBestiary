package com.example.DnDProject.Entities.Spell;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
public class SpellType {
    @Id
    private String name;
    @OneToMany(mappedBy = "spellType", orphanRemoval = true)
    private List<Spell> spells = new ArrayList<>();

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }//Getters and Setters
}
