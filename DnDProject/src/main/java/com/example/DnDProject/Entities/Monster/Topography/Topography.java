package com.example.DnDProject.Entities.Monster.Topography;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Topography {

    @Id //describes the type of terrain in which the battle will take place
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
