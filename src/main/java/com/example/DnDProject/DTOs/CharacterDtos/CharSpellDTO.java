package com.example.DnDProject.DTOs.CharacterDtos;

import java.util.List;
import java.util.Map;

public class CharSpellDTO {
    private int stat_raise;
    private Map<String, Integer> spells;
    private Map<String, Integer> plots;
    private int plots_num;
    private int spells_num;
    private List<Integer> stats;
    private String race;

    public CharSpellDTO() {}

    public CharSpellDTO(int stat_raise, Map<String, Integer> spells,Map<String, Integer> plots,
                        int spells_num, int plots_num, List<Integer> stats, String race) {
        this.plots_num = plots_num;
        this.plots = plots;
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

    public Map<String, Integer> getPlots() {
        return plots;
    }

    public void setPlots(Map<String, Integer> plots) {
        this.plots = plots;
    }

    public int getPlots_num() {
        return plots_num;
    }

    public void setPlots_num(int plots_num) {
        this.plots_num = plots_num;
    }
}
