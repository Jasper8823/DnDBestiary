package com.example.DnDProject.DTOs;

import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Monster.Location.Location;
import com.example.DnDProject.Entities.Monster.StatusSens.Status;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Monster.MonsterAttributes.Danger;
import com.example.DnDProject.Entities.Monster.MonsterAttributes.Size;
import com.example.DnDProject.Entities.Monster.MonsterAttributes.Type;
import com.example.DnDProject.Entities.Monster.MonsterAttributes.Worldview;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;

import java.util.ArrayList;
import java.util.List;

public class MonsterDTO {

    private int id;
    private String name;
    private int armor_class;
    private int speed;
    private int swim_speed;
    private int fly_speed;
    private int avg_HP;
    private String calc_HP;

    // Monster attributes
    private int strength;
    private int dexterity;
    private int charisma;
    private int intelligence;
    private int wisdom;
    private int constitution;
    private int perception;
    private int skill_bonus;
    private int strength_bonus;
    private int dexterity_bonus;
    private int charisma_bonus;
    private int intelligence_bonus;
    private int wisdom_bonus;
    private int constitution_bonus;

    private String features;
    private String description;

    private Danger danger; // Nullable
    private Size size; // Nullable
    private Type type; // Nullable
    private Worldview worldview; // Nullable

    // Connections
    private List<DamageType> immunityList = new ArrayList<>(); // Nullable
    private List<DamageType> resistanceList = new ArrayList<>(); // Nullable
    private List<DamageType> vulnerabilityList = new ArrayList<>(); // Nullable
    private List<Status> immunityStatusList = new ArrayList<>(); // Nullable
    private List<Location> habitats = new ArrayList<>(); // Nullable
    private List<CharacterClass> classAdvList = new ArrayList<>(); // Nullable
    private List<CharacterClass> classWeakList = new ArrayList<>(); // Nullable
    private List<MonsterAction> monsterActions = new ArrayList<>(); // Nullable
    private List<Topography> topographyWeakList = new ArrayList<>(); // Nullable
    private List<Topography> topographyAdvList = new ArrayList<>(); // Nullable

    // Getters and Setters

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

    public int getArmor_class() {
        return armor_class;
    }

    public void setArmor_class(int armor_class) {
        this.armor_class = armor_class;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public int getAvg_HP() {
        return avg_HP;
    }

    public void setAvg_HP(int avg_HP) {
        this.avg_HP = avg_HP;
    }

    public String getCalc_HP() {
        return calc_HP;
    }

    public void setCalc_HP(String calc_HP) {
        this.calc_HP = calc_HP;
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

    public int getConstitution_bonus() {
        return constitution_bonus;
    }

    public void setConstitution_bonus(int constitution_bonus) {
        this.constitution_bonus = constitution_bonus;
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

    public Danger getDanger() {
        return danger;
    }

    public void setDanger(Danger danger) {
        this.danger = danger;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Worldview getWorldview() {
        return worldview;
    }

    public void setWorldview(Worldview worldview) {
        this.worldview = worldview;
    }

    public List<DamageType> getImmunityList() {
        return immunityList;
    }

    public void setImmunityList(List<DamageType> immunityList) {
        this.immunityList = immunityList;
    }

    public List<DamageType> getResistanceList() {
        return resistanceList;
    }

    public void setResistanceList(List<DamageType> resistanceList) {
        this.resistanceList = resistanceList;
    }

    public List<DamageType> getVulnerabilityList() {
        return vulnerabilityList;
    }

    public void setVulnerabilityList(List<DamageType> vulnerabilityList) {
        this.vulnerabilityList = vulnerabilityList;
    }

    public List<Status> getImmunityStatusList() {
        return immunityStatusList;
    }

    public void setImmunityStatusList(List<Status> immunityStatusList) {
        this.immunityStatusList = immunityStatusList;
    }

    public List<Location> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Location> habitats) {
        this.habitats = habitats;
    }

    public List<CharacterClass> getClassAdvList() {
        return classAdvList;
    }

    public void setClassAdvList(List<CharacterClass> classAdvList) {
        this.classAdvList = classAdvList;
    }

    public List<CharacterClass> getClassWeakList() {
        return classWeakList;
    }

    public void setClassWeakList(List<CharacterClass> classWeakList) {
        this.classWeakList = classWeakList;
    }

    public List<MonsterAction> getMonsterActions() {
        return monsterActions;
    }

    public void setMonsterActions(List<MonsterAction> monsterActions) {
        this.monsterActions = monsterActions;
    }

    public List<Topography> getTopographyWeakList() {
        return topographyWeakList;
    }

    public void setTopographyWeakList(List<Topography> topographyWeakList) {
        this.topographyWeakList = topographyWeakList;
    }

    public List<Topography> getTopographyAdvList() {
        return topographyAdvList;
    }

    public void setTopographyAdvList(List<Topography> topographyAdvList) {
        this.topographyAdvList = topographyAdvList;
    }
}
