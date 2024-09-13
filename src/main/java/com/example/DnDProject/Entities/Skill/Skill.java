package com.example.DnDProject.Entities.Skill;


import com.example.DnDProject.Entities.BackStory.Backstory;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Spell.Spell;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "attribute_name", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Attribute attribute;

    @ManyToMany(mappedBy = "back_skillList")
    private List<Backstory> back_skillList = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
            name = "skill_char",
            joinColumns = { @JoinColumn(name = "skill_name") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> skill_charList = new ArrayList<>();

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

