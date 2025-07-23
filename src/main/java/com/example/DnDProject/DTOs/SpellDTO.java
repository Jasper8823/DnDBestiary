package com.example.DnDProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String prepareMoves;
    private String description;
    @JsonProperty("damageType_names")
    private List<DamageTypeDTO> damageTypes = new ArrayList<>();
    @JsonProperty("status_names")
    private List<String> status_names = new ArrayList<>();
    @JsonProperty("class_names")
    private List<String> class_names = new ArrayList<>();

    public List<DamageTypeDTO> getDamageType_names() {
        return damageTypes;
    }

    public void setDamageType_names(List<DamageTypeDTO> damageType_names) {
        this.damageTypes = damageType_names;
    }

    public List<String> getStatus_names() {
        return status_names;
    }

    public void setStatus_names(List<String> status_names) {
        this.status_names = status_names;
    }

    public List<String> getClass_names() {
        return class_names;
    }

    public void setClass_names(List<String> class_names) {
        this.class_names = class_names;
    }

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

    public String getPrepareMoves() {
        return prepareMoves;
    }

    public void setPrepareMoves(String prepareMoves) {
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
