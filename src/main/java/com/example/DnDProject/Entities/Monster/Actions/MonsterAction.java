package com.example.DnDProject.Entities.Monster.Actions;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MonsterAction {

    @Id
    private int id;

    private boolean isLegendary;
    private String information;

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public String getInformation() {
        return information;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
