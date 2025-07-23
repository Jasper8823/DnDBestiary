package com.example.DnDProject.Entities.Spell;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Monster.Status.Status;
import com.example.DnDProject.Entities.MtoMConnections.Spell_DamageType;
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
    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "level")
    private int level;

    @Column(name = "duration",length = 8)
    private String duration;
    @Column(name = "concentration")
    private boolean concentration;
    @Column(name = "concent_duration",length = 8)
    private String concentDura;
    @Column(name = "distance")
    private int distance;
    @Column(name = "target",length = 32)
    private String target;
    @Column(name = "prepare_moves")
    private String prepareMoves;
    @Column(name = "description",length = 2048)
    private String description;


    //One-to-many connections
    @ManyToOne
    @JoinColumn(name = "spell_type_name")
    private SpellType spellType;

    @OneToMany(mappedBy = "spell", orphanRemoval = true)
    private List<Spell_DamageType> spellDamageTypeList = new ArrayList<>();

    //Many-to-many connections
    @ManyToMany()
    @JoinTable(
            name = "spell_class",
            joinColumns = { @JoinColumn(name = "spell_name") },
            inverseJoinColumns = { @JoinColumn(name = "class_name") }
    )
    private List<CharacterClass> spell_classList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "spell_char",
            joinColumns = { @JoinColumn(name = "spell_name") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> spell_charList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "spell_status",
            joinColumns = { @JoinColumn(name = "spell_name") },
            inverseJoinColumns = { @JoinColumn(name = "status_name") }
    )
    private List<Status> spell_statusList = new ArrayList<>();




    //Getters and Setters
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

    public String getPrepareMoves() {
        return prepareMoves;
    }

    public void setPrepareMoves(String prepareMoves) {
        this.prepareMoves = prepareMoves;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Spell_DamageType> getSpellDamageTypeList() {
        return spellDamageTypeList;
    }

    public void setSpellDamageTypeList(List<Spell_DamageType> spellDamageTypeList) {
        this.spellDamageTypeList = spellDamageTypeList;
    }

    public List<Status> getSpell_statusList() {
        return spell_statusList;
    }

    public void setSpell_statusList(List<Status> spell_statusList) {
        this.spell_statusList = spell_statusList;
    }
    public List<CharacterClass> getSpell_classList() {
        return spell_classList;
    }

    public void setSpell_classList(List<CharacterClass> spell_classList) {
        this.spell_classList = spell_classList;
    }

    public List<Character> getSpell_charList() {
        return spell_charList;
    }

    public void setSpell_charList(List<Character> spell_charList) {
        this.spell_charList = spell_charList;
    }

}
