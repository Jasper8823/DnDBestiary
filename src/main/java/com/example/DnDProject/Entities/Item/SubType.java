package com.example.DnDProject.Entities.Item;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Race.Race;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subtype")
public class SubType {
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subType", orphanRemoval = true)
    private List<Item> items = new ArrayList<>();


    @ManyToMany()
    @JoinTable(
            name = "class_proficiency",
            joinColumns = { @JoinColumn(name = "subtype_name") },
            inverseJoinColumns = { @JoinColumn(name = "class_name") }
    )
    private List<CharacterClass> classProfList = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
            name = "race_proficiency",
            joinColumns = { @JoinColumn(name = "subtype_name") },
            inverseJoinColumns = { @JoinColumn(name = "race_name") }
    )
    private List<Race> raceProfList = new ArrayList<>();
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacterClass> getClassProfList() {
        return classProfList;
    }

    public void setClassProfList(List<CharacterClass> classProfList) {
        this.classProfList = classProfList;
    }

    public List<Race> getRaceProfList() {
        return raceProfList;
    }

    public void setRaceProfList(List<Race> raceProfList) {
        this.raceProfList = raceProfList;
    }
}

