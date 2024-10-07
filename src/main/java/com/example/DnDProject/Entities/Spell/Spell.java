package com.example.DnDProject.Entities.Spell;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.CharacterClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "spell")
public class Spell {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private int level;

    // Spell features
    @Column(name = "duration")
    private String duration;

    @Column(name = "concentration")
    private boolean concentration;

    @Column(name = "concentDura")
    private String concentDura;

    @Column(name = "distance")
    private int distance;

    @Column(name = "target")
    private String target;

    @Column(name = "prepareMoves")
    private int prepareMoves;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "spellType_name")
    private SpellType spellType;

    @ManyToMany()
    @JoinTable(
            name = "spell_class",
            joinColumns = { @JoinColumn(name = "spellname") },
            inverseJoinColumns = { @JoinColumn(name = "class_name") }
    )
    private List<CharacterClass> spell_classList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "spell_char",
            joinColumns = { @JoinColumn(name = "spellname") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> spell_charList = new ArrayList<>();

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
    }
}
