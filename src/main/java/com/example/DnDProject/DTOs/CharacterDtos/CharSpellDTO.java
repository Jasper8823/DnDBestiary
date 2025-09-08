package com.example.DnDProject.DTOs.CharacterDtos;

import java.util.List;
import java.util.Map;

public class CharSpellDTO {
    private int stat_raise;
    private Map<String, Integer> spells;
    private int spells_num;
    private List<Integer> stats;
    private String race;

    public CharSpellDTO() {}

    public CharSpellDTO(int stat_raise, Map<String, Integer> spells, int spells_num, List<Integer> stats, String race) {
        this.stats = stats;
        this.stat_raise = stat_raise;
        this.spells_num = spells_num;
        this.spells = spells;
        this.race = race;
    }

    public int getStat_raise() {
        return stat_raise;
    }

    public void setStat_raise(int stat_raise) {
        this.stat_raise = stat_raise;
    }

    public Map<String, Integer> getSpells() {
        return spells;
    }

    public void setSpells(Map<String, Integer> spells) {
        this.spells = spells;
    }

    public int getSpells_num() {
        return spells_num;
    }

    public void setSpells_num(int spells_num) {
        this.spells_num = spells_num;
    }

    public List<Integer> getStats() {return stats;}

    public void setStats(List<Integer> stats) {this.stats = stats;}

    public String getRace() {return race;}

    public void setRace(String race) {this.race = race;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stat Raise: ").append(stat_raise).append("\n");
        sb.append("Spells Num: ").append(spells_num).append("\n");
        sb.append("Spells:\n");

        if (spells != null && !spells.isEmpty()) {
            spells.forEach((name, level) ->
                    sb.append("  - ").append(name)
                            .append(" (Level ").append(level).append(")\n"));
        } else {
            sb.append("  None\n");
        }

        return sb.toString();
    }
}
