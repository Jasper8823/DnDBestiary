package com.example.DnDProject.Entities.MtoMConnections;

import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Race.Race;
import com.example.DnDProject.Entities.Skill.Attribute;

import jakarta.persistence.*;

@Entity
public class RaceAttribute {
    @Id
    private int id;
    private int modifValue;

    @ManyToOne
    @JoinColumn(name = "race_name")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "att_name")
    private Attribute attribute;
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
