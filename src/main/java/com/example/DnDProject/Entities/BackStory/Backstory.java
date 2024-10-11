package com.example.DnDProject.Entities.BackStory;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Skill.Skill;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "backstory")
public class Backstory {
    @Id
    @Column(name = "name",length = 16)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "back_skill",
            joinColumns = { @JoinColumn(name = "back_name") },
            inverseJoinColumns = { @JoinColumn(name = "skill_name") }
    )
    private List<Skill> back_skillList = new ArrayList<>();

    @OneToMany(mappedBy = "backstory", orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getBack_skillList() {
        return back_skillList;
    }

    public void setBack_skillList(List<Skill> back_skillList) {
        this.back_skillList = back_skillList;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
