package com.example.DnDProject.Entities.Class;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CharClass {

    @Id
    private String name;

    private String HP_dice;

    // One-to-Many relationship with ClassAbility
    @OneToMany(mappedBy = "charClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassAbility> abilities = new ArrayList<>();

    // Getters and Setters
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

    public List<ClassAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<ClassAbility> abilities) {
        this.abilities = abilities;
    }

}