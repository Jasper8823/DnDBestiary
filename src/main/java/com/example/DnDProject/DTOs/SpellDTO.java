package com.example.DnDProject.DTOs;

import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class SpellDTO {
    private String spellTypename;
    private String name;

    private int level;

    // Spell features
    private String duration;
    private boolean concentration;
    private String concentDura;
    private int distance;
    private String target;
    private int prepareMoves;
    private String description;

    private List<String> spell_classList = new ArrayList<>();

    public String getSpellTypename() {
        return spellTypename;
    }

    public void setSpellTypename(String spellTypename) {
        this.spellTypename = spellTypename;
    }

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

    public List<String> getSpell_classList() {
        return spell_classList;
    }

    public void setSpell_classList(List<String> spell_classList) {
        this.spell_classList = spell_classList;
    }
}
