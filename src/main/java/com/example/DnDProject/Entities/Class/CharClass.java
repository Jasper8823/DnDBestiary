package com.example.DnDProject.Entities.Class;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CharClass {

    @Id
    private String name;

    private String HP_dice;

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.ALL)
    private List<ClassAbility> abilities = new ArrayList<>();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHP_dice() {
        return HP_dice;
    }

    public void setHP_dice(String HP_dice) {
        this.HP_dice = HP_dice;
    }

    public List<ClassAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<ClassAbility> abilities) {
        this.abilities = abilities;
    }
}
