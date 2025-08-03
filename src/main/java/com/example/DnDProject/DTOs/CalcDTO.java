package com.example.DnDProject.DTOs;

import java.util.List;

public class CalcDTO {
    private List<PlayerGroup> players;
    private List<MonsterGroup> monsters;

    public List<PlayerGroup> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerGroup> players) {
        this.players = players;
    }

    public List<MonsterGroup> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<MonsterGroup> monsters) {
        this.monsters = monsters;
    }

    public static class PlayerGroup {
        private int count;
        private int level;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    public static class MonsterGroup {
        private int count;
        private String cr;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCr() {
            return cr;
        }

        public void setCr(String cr) {
            this.cr = cr;
        }
    }
}
