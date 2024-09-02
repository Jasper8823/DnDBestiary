package com.example.DnDProject.Entities.Class;

import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Skill.Attribute;
import com.example.DnDProject.Entities.Spell.Spell;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CharacterClass {

    @Id
    private String name;

    private String HP_dice;

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<ClassAbility> abilityList = new ArrayList<>();

    @OneToMany(mappedBy = "charClass", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Character> characterList = new ArrayList<>();

    @ManyToMany(mappedBy = "classAdvList")
    private List<Monster> monsters_advC = new ArrayList<>();
    @ManyToMany(mappedBy = "classWeakList")
    private List<Monster> monsters_weakC = new ArrayList<>();

    @ManyToMany(mappedBy = "att_classList")
    private List<Attribute> att_classList = new ArrayList<>();

    @ManyToMany(mappedBy = "spell_classList")
    private List<Spell> spell_classList = new ArrayList<>();

    // Getters and Setters
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

}
