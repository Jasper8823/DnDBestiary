package com.example.DnDProject.Entities.MtoMConnections;

import com.example.DnDProject.Entities.Race.Race;
import com.example.DnDProject.Entities.Attribute.Attribute;

import jakarta.persistence.*;

@Entity
@Table(name = "race_attribute")
public class RaceAttribute {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private int id;

    @Column(name = "modif_value")
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
