package com.example.DnDProject.Entities.Class;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
public class ClassAbility {

    @Id
    private String name;

    private String description;
    private int level;

    @ManyToOne
    @JoinColumn(name = "class_name")
    private CharacterClass charClass;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
