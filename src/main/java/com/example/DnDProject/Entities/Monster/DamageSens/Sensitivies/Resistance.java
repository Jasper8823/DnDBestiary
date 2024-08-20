package com.example.DnDProject.Entities.Monster.DamageSens.Sensitivies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resistance {

    @Id //some monsters may have resistance to damage of a certain type
        //example: demons take half the damage
    private int Id;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
