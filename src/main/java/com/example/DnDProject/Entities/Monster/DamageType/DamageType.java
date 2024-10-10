package com.example.DnDProject.Entities.Monster.DamageType;

import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import com.example.DnDProject.Entities.Spell.Spell;
import jakarta.persistence.*;

@Entity
@Table(name = "damage_type")
public class DamageType {

    @Id
    private String name;

    @ManyToMany(mappedBy = "immunityList")
    private List<Monster> monster_imD =  new ArrayList<>();

    @ManyToMany(mappedBy = "resistanceList")
    private List<Monster> monster_res =  new ArrayList<>();

    @ManyToMany(mappedBy = "vulnerabilityList")
    private List<Monster> monster_vul =  new ArrayList<>();//Sensitivities connections

    @ManyToMany(mappedBy = "spell_damTypeList")
    private List<Spell> spell_damTypeList = new ArrayList<>();

    @ManyToMany(mappedBy = "item_damTypeList")
    private List<Item> item_damTypeList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters

    public List<Monster> getMonster_imD() {
        return monster_imD;
    }

    public void setMonster_imD(List<Monster> monster_imD) {
        this.monster_imD = monster_imD;
    }

    public List<Monster> getMonster_res() {
        return monster_res;
    }

    public void setMonster_res(List<Monster> monster_res) {
        this.monster_res = monster_res;
    }

    public List<Monster> getMonster_vul() {
        return monster_vul;
    }

    public void setMonster_vul(List<Monster> monster_vul) {
        this.monster_vul = monster_vul;
    }

    public List<Spell> getSpell_damTypeList() {
        return spell_damTypeList;
    }

    public void setSpell_damTypeList(List<Spell> spell_damTypeList) {
        this.spell_damTypeList = spell_damTypeList;
    }

    public List<Item> getItem_damTypeList() {
        return item_damTypeList;
    }

    public void setItem_damTypeList(List<Item> item_damTypeList) {
        this.item_damTypeList = item_damTypeList;
    }
}
