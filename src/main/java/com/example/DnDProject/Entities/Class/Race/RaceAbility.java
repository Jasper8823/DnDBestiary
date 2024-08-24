package com.example.DnDProject.Entities.Class.Race;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RaceAbility {
    @Id
    private String name;
    private String description;

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
}
