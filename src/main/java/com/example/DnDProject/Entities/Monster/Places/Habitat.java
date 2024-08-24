package com.example.DnDProject.Entities.Monster.Places;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Habitat {

    @Id //describes the locations and biomes in which the monster is most often found
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }//Getters and Setters
}
