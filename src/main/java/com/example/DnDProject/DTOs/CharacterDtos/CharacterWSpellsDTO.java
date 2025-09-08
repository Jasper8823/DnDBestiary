package com.example.DnDProject.DTOs.CharacterDtos;

import java.util.List;

public class CharacterWSpellsDTO {

    private List<Integer> stats;
    private List<String> spells;
    private String uuid;

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }

    public List<String> getSpells() {
        return spells;
    }

    public void setSpells(List<String> spells) {
        this.spells = spells;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
