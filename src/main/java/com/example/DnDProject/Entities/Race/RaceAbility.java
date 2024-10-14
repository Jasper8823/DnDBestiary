package com.example.DnDProject.Entities.Race;

import jakarta.persistence.*;

@Entity
@Table(name = "race_ability")
public class RaceAbility {
    @Id
    private int id;

    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "description",length = 2048)
    private String description;

    @ManyToOne
    @JoinColumn(name = "race_name")
    private Race race;

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
