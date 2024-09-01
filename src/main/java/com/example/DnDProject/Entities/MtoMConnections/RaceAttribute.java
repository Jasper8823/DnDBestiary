package com.example.DnDProject.Entities.MtoMConnections;

import javax.persistence.*;

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
