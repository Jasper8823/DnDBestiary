package com.example.DnDProject.Entities.Monster.StatusSens;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ImmunityStatus {

    @Id //monsters may be immune to certain conditions
        //example: skeleton will not be affected by poison
    private int Id;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
