package com.example.DnDProject.Entities.Class;

import jakarta.persistence.*;

@Entity
public class SpellSlots {
    @Id
    @GeneratedValue
    private int id;
    private int level;
    private int lvl1;
    private int lvl2;
    private int lvl3;
    private int lvl4;
    private int lvl5;
    private int lvl6;
    private int lvl7;
    private int lvl8;
    private int lvl9;
    private int plot_num;

    private int spell_num;


    @ManyToOne
    @JoinColumn(name = "class_name")
    private CharacterClass charClass;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLvl1() {
        return lvl1;
    }

    public void setLvl1(int lvl1) {
        this.lvl1 = lvl1;
    }

    public int getLvl2() {
        return lvl2;
    }

    public void setLvl2(int lvl2) {
        this.lvl2 = lvl2;
    }

    public int getLvl3() {
        return lvl3;
    }

    public void setLvl3(int lvl3) {
        this.lvl3 = lvl3;
    }

    public int getLvl4() {
        return lvl4;
    }

    public void setLvl4(int lvl4) {
        this.lvl4 = lvl4;
    }

    public int getLvl5() {
        return lvl5;
    }

    public void setLvl5(int lvl5) {
        this.lvl5 = lvl5;
    }

    public int getLvl6() {
        return lvl6;
    }

    public void setLvl6(int lvl6) {
        this.lvl6 = lvl6;
    }

    public int getLvl7() {
        return lvl7;
    }

    public void setLvl7(int lvl7) {
        this.lvl7 = lvl7;
    }

    public int getLvl8() {
        return lvl8;
    }

    public void setLvl8(int lvl8) {
        this.lvl8 = lvl8;
    }

    public int getLvl9() {
        return lvl9;
    }

    public void setLvl9(int lvl9) {
        this.lvl9 = lvl9;
    }

    public int getPlot_num() {
        return plot_num;
    }

    public void setPlot_num(int plot_num) {
        this.plot_num = plot_num;
    }

    public int getSpell_num() {
        return spell_num;
    }

    public void setSpell_num(int spell_num) {
        this.spell_num = spell_num;
    }

    public CharacterClass getCharClass() {
        return charClass;
    }

    public void setCharClass(CharacterClass charClass) {
        this.charClass = charClass;
    }
}

