package com.example.DnDProject.Entities.Class;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Item.SubType;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Attribute.Attribute;
import com.example.DnDProject.Entities.Spell.Spell;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class")
public class CharacterClass {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "hp_dice")
    private String HP_dice;

    @ManyToOne
    @JoinColumn(name = "parent_name") // Foreign key referencing itself
    private CharacterClass parentClass;

    @OneToMany(mappedBy = "parentClass")
    private List<CharacterClass> childClasses = new ArrayList<>();

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<ClassAbility> abilityList = new ArrayList<>();

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Character> characterList = new ArrayList<>();

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<SpellSlots> spellSlotsList = new ArrayList<>();

    @ManyToMany(mappedBy = "classAdvList")
    private List<Monster> monsters_advC = new ArrayList<>();

    @ManyToMany(mappedBy = "classWeakList")
    private List<Monster> monsters_weakC = new ArrayList<>();

    @ManyToMany(mappedBy = "att_classList")
    private List<Attribute> att_classList = new ArrayList<>();

    @ManyToMany(mappedBy = "spell_classList")
    private List<Spell> spell_classList = new ArrayList<>();

    @ManyToMany(mappedBy = "classProfList")
    private List<SubType> subtype_classList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHP_dice() {
        return HP_dice;
    }

    public void setHP_dice(String HP_dice) {
        this.HP_dice = HP_dice;
    }

    public CharacterClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(CharacterClass parentClass) {
        this.parentClass = parentClass;
    }

    public List<CharacterClass> getChildClasses() {
        return childClasses;
    }

    public void setChildClasses(List<CharacterClass> childClasses) {
        this.childClasses = childClasses;
    }

    public List<ClassAbility> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<ClassAbility> abilityList) {
        this.abilityList = abilityList;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public List<Monster> getMonstersAdvC() {
        return monsters_advC;
    }

    public void setMonstersAdvC(List<Monster> monsters_advC) {
        this.monsters_advC = monsters_advC;
    }

    public List<Monster> getMonstersWeakC() {
        return monsters_weakC;
    }

    public void setMonstersWeakC(List<Monster> monsters_weakC) {
        this.monsters_weakC = monsters_weakC;
    }

    public List<Attribute> getAttClassList() {
        return att_classList;
    }

    public void setAttClassList(List<Attribute> att_classList) {
        this.att_classList = att_classList;
    }

    public List<Spell> getSpellClassList() {
        return spell_classList;
    }

    public void setSpellClassList(List<Spell> spell_classList) {
        this.spell_classList = spell_classList;
    }

    public List<SpellSlots> getSpellSlotsList() {
        return spellSlotsList;
    }

    public void setSpellSlotsList(List<SpellSlots> spellSlotsList) {
        this.spellSlotsList = spellSlotsList;
    }

    public List<Monster> getMonsters_advC() {
        return monsters_advC;
    }

    public void setMonsters_advC(List<Monster> monsters_advC) {
        this.monsters_advC = monsters_advC;
    }

    public List<Monster> getMonsters_weakC() {
        return monsters_weakC;
    }

    public void setMonsters_weakC(List<Monster> monsters_weakC) {
        this.monsters_weakC = monsters_weakC;
    }

    public List<Attribute> getAtt_classList() {
        return att_classList;
    }

    public void setAtt_classList(List<Attribute> att_classList) {
        this.att_classList = att_classList;
    }

    public List<Spell> getSpell_classList() {
        return spell_classList;
    }

    public void setSpell_classList(List<Spell> spell_classList) {
        this.spell_classList = spell_classList;
    }
}
