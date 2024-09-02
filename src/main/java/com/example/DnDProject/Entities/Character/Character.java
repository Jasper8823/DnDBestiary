package com.example.DnDProject.Entities.Character;

import com.example.DnDProject.Entities.BackStory.Backstory;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Race.Race;
import com.example.DnDProject.Entities.Skill.Skill;
import com.example.DnDProject.Entities.Spell.Spell;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Character {
    @Id
    private int id;
    private String name;
    private int level;

    //Character's attributes
    private int strength;
    private int dexterity;
    private int charisma;
    private int intelligence;
    private int wisdom;
    private int constitution;


    private int speed;
    private int hp;


    @ManyToMany(mappedBy = "spell_charList")
    private List<Spell> spell_charList = new ArrayList<>();

    @ManyToMany(mappedBy = "item_charList")
    private List<Spell> item_charList = new ArrayList<>();

    @ManyToMany(mappedBy = "skill_charList")
    private List<Skill> skill_charList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "class_name")
    private CharacterClass charClass;

    @ManyToOne
    @JoinColumn(name = "race_name")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "backstory_name")
    private Backstory backstory;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    } // Getters and Setters

}
