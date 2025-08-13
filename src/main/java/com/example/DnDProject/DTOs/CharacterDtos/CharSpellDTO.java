package com.example.DnDProject.DTOs.CharacterDtos;

import java.util.Map;

public class CharSpellDTO {
    private int stat_raise;
    private Map<String, Integer> spells;
    private int spells_num;

    public CharSpellDTO() {}

    public CharSpellDTO(int stat_raise, Map<String, Integer> spells, int spells_num) {
        this.stat_raise = stat_raise;
        this.spells_num = spells_num;
        this.spells = spells;
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
