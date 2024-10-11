package com.example.DnDProject.Entities.Trait;

import com.example.DnDProject.Entities.Character.Character;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trait")
public class Trait {
    @Id
    @Column(name = "name",length = 32)

    private String name;

    @Column(name = "description",length = 1024)
    private String description;

    @ManyToMany()
    @JoinTable(
            name = "trait_char",
            joinColumns = { @JoinColumn(name = "trait_name") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> trait_charList = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getTrait_charList() {
        return trait_charList;
    }

    public void setTrait_charList(List<Character> trait_charList) {
        this.trait_charList = trait_charList;
    }
}