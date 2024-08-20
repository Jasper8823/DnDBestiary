package com.example.DnDProject.Entities.Monster.StatusSens;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Status {

    @Id
    private int Id;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
