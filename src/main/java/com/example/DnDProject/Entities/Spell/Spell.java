package com.example.DnDProject.Entities.Spell;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Spell {

    @Id
    private String name;

    private int level;

    private String duration;
    private boolean concentration;
    private String concentDura;
    private int distance;
    private String target;
    private int prepareMoves;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getConcentDura() {
        return concentDura;
    }

    public void setConcentDura(String concentDura) {
        this.concentDura = concentDura;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPrepareMoves() {
        return prepareMoves;
    }

    public void setPrepareMoves(int prepareMoves) {
        this.prepareMoves = prepareMoves;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
