package com.example.DnDProject.Entities.Race;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Race {
    @Id
    private String name;
    @OneToMany(mappedBy = "race", orphanRemoval = true)
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
