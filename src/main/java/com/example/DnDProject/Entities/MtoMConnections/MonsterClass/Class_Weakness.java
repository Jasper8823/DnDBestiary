package com.example.DnDProject.Entities.MtoMConnections.MonsterClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Class_Weakness {
    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
