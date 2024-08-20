package com.example.DnDProject.Entities.Monster.ClassSensitivities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Class_Weakness {

    @Id //some monsters may have an disadvantage in combat against a character of a certain class
        //example: paladin will have an easy time fighting the undead
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
