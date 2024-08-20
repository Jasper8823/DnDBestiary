package com.example.DnDProject.Entities.Monster.DamageSens.Sensitivies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ImmunityDamage {

    @Id //some monsters may not take damage of a certain type at all
        //example: all elementals do not take poison damage
    private int Id;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
