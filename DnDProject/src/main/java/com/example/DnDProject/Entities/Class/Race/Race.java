package com.example.DnDProject.Entities.Class.Race;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Race {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
