package com.example.DnDProject.Entities.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CharClass {

    @Id
    private String name;

    private String HP_dice;

    public void setName(String name) {
        this.name = name;
    }

    public void setHP_dice(String HP_dice) {
        this.HP_dice = HP_dice;
    }

    public String getName() {
        return name;
    }

    public String getHP_dice() {
        return HP_dice;
    }
}
