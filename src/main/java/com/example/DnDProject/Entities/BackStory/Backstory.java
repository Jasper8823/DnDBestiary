package com.example.DnDProject.Entities.BackStory;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Skill.Skill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backstory {
    @Id
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
}
