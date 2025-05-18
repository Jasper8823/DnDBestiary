package com.example.DnDProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class MonsterDTO {
    private String name;

    private int armor_class;

    private int walk_speed;
    private int swim_speed;
    private int fly_speed;

    private int numberofdice;
    private int dicetype;
    private int passivebonus;

    // Monster attributes
    private int strength;
    private int dexterity;
    private int charisma;
    private int intelligence;
    private int wisdom;
    private int bodybuild;

    private int perception;
    private int skill_bonus;

    private int strength_bonus;
    private int dexterity_bonus;
    private int charisma_bonus;
    private int intelligence_bonus;
    private int wisdom_bonus;
    private int bodybuild_bonus;

    private String features;
    private String description;

    private int danger;
    private String size;
    private String type;
    private String worldview;

    // Connections
    @JsonProperty("immunityList")
    private List<String> immunityList = new ArrayList<>();
    @JsonProperty("resistanceList")
    private List<String> resistanceList = new ArrayList<>();
    @JsonProperty("vulnerabilityList")
    private List<String> vulnerabilityList = new ArrayList<>();
    @JsonProperty("immunityStatusList")
    private List<String> immunityStatusList = new ArrayList<>();
    @JsonProperty("habitats")
    private List<String> habitats = new ArrayList<>();
    @JsonProperty("classAdvList")
    private List<String> classAdvList = new ArrayList<>();
    @JsonProperty("classWeakList")
    private List<String> classWeakList = new ArrayList<>();
    @JsonProperty("actions")
    private List<ActionDTO> actions = new ArrayList<>();
    @JsonProperty("topographyWeakList")
    private List<String> topographyWeakList = new ArrayList<>();
    @JsonProperty("topographyAdvList")
    private List<String> topographyAdvList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmor_class() {
        return armor_class;
    }

    public void setArmor_class(int armor_class) {
        this.armor_class = armor_class;
    }

    public int getWalk_speed() {
        return walk_speed;
    }

    public void setWalk_speed(int walk_speed) {
        this.walk_speed = walk_speed;
    }

    public int getSwim_speed() {
        return swim_speed;
    }

    public void setSwim_speed(int swim_speed) {
        this.swim_speed = swim_speed;
    }

    public int getFly_speed() {
        return fly_speed;
    }

    public void setFly_speed(int fly_speed) {
        this.fly_speed = fly_speed;
    }

    public int getNumberofdice() {
        return numberofdice;
    }

    public void setNumberofdice(int numberofdice) {
        this.numberofdice = numberofdice;
    }

    public int getDicetype() {
        return dicetype;
    }

    public void setDicetype(int dicetype) {
        this.dicetype = dicetype;
    }

    public int getPassivebonus() {
        return passivebonus;
    }

    public void setPassivebonus(int passivebonus) {
        this.passivebonus = passivebonus;
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

    public int getBodybuild() {
        return bodybuild;
    }

    public void setBodybuild(int bodybuild) {
        this.bodybuild = bodybuild;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getSkill_bonus() {
        return skill_bonus;
    }

    public void setSkill_bonus(int skill_bonus) {
        this.skill_bonus = skill_bonus;
    }

    public int getStrength_bonus() {
        return strength_bonus;
    }

    public void setStrength_bonus(int strength_bonus) {
        this.strength_bonus = strength_bonus;
    }

    public int getDexterity_bonus() {
        return dexterity_bonus;
    }

    public void setDexterity_bonus(int dexterity_bonus) {
        this.dexterity_bonus = dexterity_bonus;
    }

    public int getCharisma_bonus() {
        return charisma_bonus;
    }

    public void setCharisma_bonus(int charisma_bonus) {
        this.charisma_bonus = charisma_bonus;
    }

    public int getIntelligence_bonus() {
        return intelligence_bonus;
    }

    public void setIntelligence_bonus(int intelligence_bonus) {
        this.intelligence_bonus = intelligence_bonus;
    }

    public int getWisdom_bonus() {
        return wisdom_bonus;
    }

    public void setWisdom_bonus(int wisdom_bonus) {
        this.wisdom_bonus = wisdom_bonus;
    }

    public int getBodybuild_bonus() {
        return bodybuild_bonus;
    }

    public void setBodybuild_bonus(int bodybuild_bonus) {
        this.bodybuild_bonus = bodybuild_bonus;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDanger() {
        return danger;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String  size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorldview() {
        return worldview;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public List<String> getImmunityList() {
        return immunityList;
    }

    public void setImmunityList(List<String> immunityList) {
        this.immunityList = immunityList;
    }

    public List<String> getResistanceList() {
        return resistanceList;
    }

    public void setResistanceList(List<String> resistanceList) {
        this.resistanceList = resistanceList;
    }

    public List<String> getVulnerabilityList() {
        return vulnerabilityList;
    }

    public void setVulnerabilityList(List<String> vulnerabilityList) {
        this.vulnerabilityList = vulnerabilityList;
    }

    public List<String> getImmunityStatusList() {
        return immunityStatusList;
    }

    public void setImmunityStatusList(List<String> immunityStatusList) {
        this.immunityStatusList = immunityStatusList;
    }

    public List<String> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<String> habitats) {
        this.habitats = habitats;
    }

    public List<String> getClassAdvList() {
        return classAdvList;
    }

    public void setClassAdvList(List<String> classAdvList) {
        this.classAdvList = classAdvList;
    }

    public List<String> getClassWeakList() {
        return classWeakList;
    }

    public void setClassWeakList(List<String> classWeakList) {
        this.classWeakList = classWeakList;
    }

    public List<ActionDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionDTO> actions) {
        this.actions = actions;
    }

    public List<String> getTopographyWeakList() {
        return topographyWeakList;
    }

    public void setTopographyWeakList(List<String> topographyWeakList) {
        this.topographyWeakList = topographyWeakList;
    }

    public List<String> getTopographyAdvList() {
        return topographyAdvList;
    }

    public void setTopographyAdvList(List<String> topographyAdvList) {
        this.topographyAdvList = topographyAdvList;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", armor_class=" + armor_class +
                ", walk_speed=" + walk_speed +
                ", swim_speed=" + swim_speed +
                ", fly_speed=" + fly_speed +
                ", numberofdice=" + numberofdice +
                ", dicetype=" + dicetype +
                ", passivebonus=" + passivebonus +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", charisma=" + charisma +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", bodybuild=" + bodybuild +
                ", perception=" + perception +
                ", skill_bonus=" + skill_bonus +
                ", strength_bonus=" + strength_bonus +
                ", dexterity_bonus=" + dexterity_bonus +
                ", charisma_bonus=" + charisma_bonus +
                ", intelligence_bonus=" + intelligence_bonus +
                ", wisdom_bonus=" + wisdom_bonus +
                ", bodybuild_bonus=" + bodybuild_bonus +
                ", features='" + features + '\'' +
                ", description='" + description + '\'' +
                ", danger=" + danger +
                ", size=" + size +
                ", type=" + type +
                ", worldview=" + worldview +
                ", immunityList=" + immunityList +
                ", resistanceList=" + resistanceList +
                ", vulnerabilityList=" + vulnerabilityList +
                ", immunityStatusList=" + immunityStatusList +
                ", habitats=" + habitats +
                ", classAdvList=" + classAdvList +
                ", classWeakList=" + classWeakList +
                ", actions=" + actions +
                ", topographyWeakList=" + topographyWeakList +
                ", topographyAdvList=" + topographyAdvList +
                '}';
    }

}
