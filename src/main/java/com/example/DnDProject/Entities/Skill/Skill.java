package com.example.DnDProject.Entities.Skill;

import com.example.DnDProject.Entities.Race.Race;
import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_name")
    private Attribute attribute;

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
    } //Getters and Setters
}
