package com.example.DnDProject.Entities.Monster.Places;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
