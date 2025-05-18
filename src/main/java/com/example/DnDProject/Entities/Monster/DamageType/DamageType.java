package com.example.DnDProject.Entities.Monster.DamageType;

import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import com.example.DnDProject.Entities.MtoMConnections.Item_DamageType;
import com.example.DnDProject.Entities.MtoMConnections.Spell_DamageType;
import com.example.DnDProject.Entities.Race.RaceAbility;
import com.example.DnDProject.Entities.Spell.Spell;
import jakarta.persistence.*;

@Entity
@Table(name = "damage_type")
public class DamageType {

    @Id
    @Column(name = "name",length = 16)
    private String name;


    //Many-to-many connections
    @ManyToMany(mappedBy = "immunityList")
    private List<Monster> monster_imD =  new ArrayList<>();

    @ManyToMany(mappedBy = "resistanceList")
    private List<Monster> monster_res =  new ArrayList<>();

    @ManyToMany(mappedBy = "vulnerabilityList")
    private List<Monster> monster_vul =  new ArrayList<>();

    @OneToMany(mappedBy = "damageType", orphanRemoval = true)
    private List<Item_DamageType> itemDamageTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "damageType", orphanRemoval = true)
    private List<Spell_DamageType> spellDamageTypeList = new ArrayList<>();



    //Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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

    public List<Item_DamageType> getItemDamageTypeList() {
        return itemDamageTypeList;
    }

    public void setItemDamageTypeList(List<Item_DamageType> itemDamageTypeList) {
        this.itemDamageTypeList = itemDamageTypeList;
    }

    public List<Spell_DamageType> getSpellDamageTypeList() {
        return spellDamageTypeList;
    }

    public void setSpellDamageTypeList(List<Spell_DamageType> spellDamageTypeList) {
        this.spellDamageTypeList = spellDamageTypeList;
    }
}
