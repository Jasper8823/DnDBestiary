package com.example.DnDProject.Entities.Monster;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Monster {

    @Id
    private int id;
    private String name;

    private int armor_class;

    //speed types
    private int speed;
    private int swim_speed;
    private int fly_speed;

    //calculated (x*y)/2+z
    private int avg_HP;
    //has form of xdy+z, where x - number of roll, y - maximum number, z - permanent bonus
    private String calc_HP;

    //monster attributes
    private int strength;
    private int dexterity;
    private int charisma;
    private int intelligence;
    private int wisdom;
    private int constitution;

    private int perception;
    private int skill_bonus;

    //monster attributes bonus
    private int strength_bonus;
    private int dexterity_bonus;
    private int charisma_bonus;
    private int intelligence_bonus;
    private int wisdom_bonus;
    private int constitution_bonus;

    private String features;
    private String description;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArmor_class(int armor_class) {
        this.armor_class = armor_class;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSwim_speed(int swim_speed) {
        this.swim_speed = swim_speed;
    }

    public void setFly_speed(int fly_speed) {
        this.fly_speed = fly_speed;
    }

    public void setAvg_HP(int avg_HP) {
        this.avg_HP = avg_HP;
    }

    public void setCalc_HP(String calc_HP) {
        this.calc_HP = calc_HP;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public void setSkill_bonus(int skill_bonus) {
        this.skill_bonus = skill_bonus;
    }

    public void setStrength_bonus(int strength_bonus) {
        this.strength_bonus = strength_bonus;
    }

    public void setDexterity_bonus(int dexterity_bonus) {
        this.dexterity_bonus = dexterity_bonus;
    }

    public void setCharisma_bonus(int charisma_bonus) {
        this.charisma_bonus = charisma_bonus;
    }

    public void setIntelligence_bonus(int intelligence_bonus) {
        this.intelligence_bonus = intelligence_bonus;
    }

    public void setWisdom_bonus(int wisdom_bonus) {
        this.wisdom_bonus = wisdom_bonus;
    }

    public void setConstitution_bonus(int constitution_bonus) {
        this.constitution_bonus = constitution_bonus;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getArmor_class() {
        return armor_class;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSwim_speed() {
        return swim_speed;
    }

    public int getFly_speed() {
        return fly_speed;
    }

    public int getAvg_HP() {
        return avg_HP;
    }

    public String getCalc_HP() {
        return calc_HP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getPerception() {
        return perception;
    }

    public int getSkill_bonus() {
        return skill_bonus;
    }

    public int getStrength_bonus() {
        return strength_bonus;
    }

    public int getDexterity_bonus() {
        return dexterity_bonus;
    }

    public int getCharisma_bonus() {
        return charisma_bonus;
    }

    public int getIntelligence_bonus() {
        return intelligence_bonus;
    }

    public int getWisdom_bonus() {
        return wisdom_bonus;
    }

    public int getConstitution_bonus() {
        return constitution_bonus;
    }

    public String getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }
}
