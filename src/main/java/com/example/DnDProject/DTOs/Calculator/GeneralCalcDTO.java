package com.example.DnDProject.DTOs.Calculator;

import java.util.List;

public class GeneralCalcDTO {
    private List<CharacterDTO> players;
    private List<MonsterDTO> monsters;
    private String topography;

    // Getters & Setters
    public List<CharacterDTO> getPlayers() { return players; }
    public void setPlayers(List<CharacterDTO> players) { this.players = players; }

    public List<MonsterDTO> getMonsters() { return monsters; }
    public void setMonsters(List<MonsterDTO> monsters) { this.monsters = monsters; }

    public String getTopography() { return topography; }
    public void setTopography(String topography) { this.topography = topography; }
}
