package com.example.DnDProject.Entities.MtoMConnections;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RaceAttribute {
    @Id
    private int id;
    private int modifValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModifValue() {
        return modifValue;
    }

    public void setModifValue(int modifValue) {
        this.modifValue = modifValue;
    }
}
