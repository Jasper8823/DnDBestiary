package com.example.DnDProject.Entities.Race;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Race {
    @Id
    private String name;
    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RaceAbility> abilities = new ArrayList<>();

    public List<RaceAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<RaceAbility> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }//Getters and Setters
}
