package com.example.DnDProject.Entities.Monster.DamageSens.Sensitivies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resistance {
    @Id
    private int Id;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
