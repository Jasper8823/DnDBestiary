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


    //One-to-many connections
    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<RaceAbility> abilities = new ArrayList<>();

    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private List<RaceAttribute> raceAttributeList = new ArrayList<>();


    //Many-to-many connections
    @ManyToMany(mappedBy = "raceProfList")
    private List<SubType> raceProfList = new ArrayList<>();


    //Getters and Setters
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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<RaceAttribute> getRaceAttributeList() {
        return raceAttributeList;
    }

    public void setRaceAttributeList(List<RaceAttribute> raceAttributeList) {this.raceAttributeList = raceAttributeList;}

    public List<SubType> getRaceProfList() {
        return raceProfList;
    }

    public void setRaceProfList(List<SubType> raceProfList) {
        this.raceProfList = raceProfList;
    }
}
