package com.example.DnDProject.Entities.Monster.ClassSensitivities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Class_Advantage {

    @Id //some monsters may have an advantage in battle against a character of a certain class
        //example: a warrior will have a hard time fighting ghosts
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}