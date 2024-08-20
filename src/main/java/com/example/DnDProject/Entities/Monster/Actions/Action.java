package com.example.DnDProject.Entities.Monster.Actions;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Action {

    @Id
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
