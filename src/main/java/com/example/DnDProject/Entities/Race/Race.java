package com.example.DnDProject.Entities.Race;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Item.SubType;
import com.example.DnDProject.Entities.MtoMConnections.RaceAttribute;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "race")
public class Race {
    @Id
    @Column(name = "name",length = 32)
    private String name;

    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<RaceAbility> abilities = new ArrayList<>();

    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<RaceAttribute> raceAttributeList = new ArrayList<>();

    @ManyToMany(mappedBy = "raceProfList")
    private List<SubType> raceProfList = new ArrayList<>();

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
    }
}
