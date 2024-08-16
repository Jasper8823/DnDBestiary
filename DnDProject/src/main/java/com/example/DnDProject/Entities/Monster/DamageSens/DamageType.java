package com.example.DnDProject.Entities.Monster.DamageSens;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DamageType {

    @Id
    private int Id;

    private String name;

    public void setdId(int dId) {
        this.Id = dId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getdId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
