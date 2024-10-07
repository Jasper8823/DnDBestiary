package com.example.DnDProject.Entities.Attribute;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.MtoMConnections.RaceAttribute;
import com.example.DnDProject.Entities.Skill.Skill;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "attribute", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "att_class",
            joinColumns = { @JoinColumn(name = "att_name") },
            inverseJoinColumns = { @JoinColumn(name = "class_name") }
    )
    private List<CharacterClass> att_classList = new ArrayList<>();

    @OneToMany(mappedBy = "attribute", orphanRemoval = true)
    private List<RaceAttribute> raceAttributeList = new ArrayList<>();

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
