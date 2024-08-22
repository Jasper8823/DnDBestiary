package com.example.DnDProject.Entities.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClassAbility {
    @Id
    private String name;
    private String description;
    private int level;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }
}
