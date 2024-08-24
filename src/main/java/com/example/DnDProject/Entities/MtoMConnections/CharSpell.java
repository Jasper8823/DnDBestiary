package com.example.DnDProject.Entities.MtoMConnections;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CharSpell {
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
