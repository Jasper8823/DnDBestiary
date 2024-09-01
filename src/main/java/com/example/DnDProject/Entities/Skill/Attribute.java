package com.example.DnDProject.Entities.Skill;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute {
    @Id
    private String name;

    @OneToMany(mappedBy = "attribute", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Skill> skills = new ArrayList<>();

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
