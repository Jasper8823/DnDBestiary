package com.example.DnDProject.Entities.Spell;

import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spell {

    @Id
    private String name;

    private int level;

    //Spell features
    private String duration;

    private boolean concentration;
    private String concentDura;
    private int distance;
    private String target;
    private int prepareMoves;

    private String description;

    @ManyToOne
    @JoinColumn(name = "spellType_name")
    private SpellType spellType;

    @ManyToMany()
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "vulnerability",
            joinColumns = { @JoinColumn(name = "monster_id") },
            inverseJoinColumns = { @JoinColumn(name = "damageType_id") }
    )
    private List<DamageType> vulnerabilityList = new ArrayList<>();

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getConcentDura() {
        return concentDura;
    }

    public void setConcentDura(String concentDura) {
        this.concentDura = concentDura;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPrepareMoves() {
        return prepareMoves;
    }

    public void setPrepareMoves(int prepareMoves) {
        this.prepareMoves = prepareMoves;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }//Getters and Setters
}
