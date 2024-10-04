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

    private Danger danger; // Nullable
    private Size size; // Nullable
    private Type type; // Nullable
    private Worldview worldview; // Nullable

    // Connections
    private List<String> immunityList = new ArrayList<>(); // Nullable
    private List<String> resistanceList = new ArrayList<>(); // Nullable
    private List<String> vulnerabilityList = new ArrayList<>(); // Nullable

    private List<String> immunityStatusList = new ArrayList<>(); // Nullable

    private List<String> habitats = new ArrayList<>(); // Nullable

    private List<String> classAdvList = new ArrayList<>(); // Nullable
    private List<String> classWeakList = new ArrayList<>(); // Nullable

    private List<List<String>> actions = new ArrayList<>(); // Nullable

    private List<String> topographyWeakList = new ArrayList<>(); // Nullable
    private List<String> topographyAdvList = new ArrayList<>(); // Nullable
}
