package com.example.DnDProject.DTOs.CharacterDtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class CharacterDTO {
    private String name;
    private int level;
    private String race;
    private String backstory;
    private String sessionid;

    @JsonProperty("class")
    private String clazz;

    private String archetype;

    private int[] stats;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionId(String sessionid) {
        this.sessionid = sessionid;
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

    @Override
    public String toString() {
        return "CharacterDTO{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", race='" + race + '\'' +
                ", backstory='" + backstory + '\'' +
                ", sessionid='" + sessionid + '\'' +
                ", clazz='" + clazz + '\'' +
                ", archetype='" + archetype + '\'' +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }
}
