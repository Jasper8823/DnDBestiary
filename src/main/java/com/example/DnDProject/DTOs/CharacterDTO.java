package com.example.DnDProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterDTO {
    private String name;
    private int level;
    private String race;
    private String backstory;

    @JsonProperty("class")
    private String clazz;

    private String archetype;

    private int[] stats;

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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        if (stats == null || stats.length != 6) {
            throw new IllegalArgumentException("Stats array must have exactly 6 elements.");
        }
        this.stats = stats;
    }
}
