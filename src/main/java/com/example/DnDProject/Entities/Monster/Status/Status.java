package com.example.DnDProject.Entities.Monster.Status;

import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import com.example.DnDProject.Entities.Spell.Spell;
import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "name",length = 16)
    private String name;

    @ManyToMany(mappedBy = "immunityStatusList")
    private List<Monster> monster_imS = new ArrayList<>();

    @ManyToMany(mappedBy = "item_statusList")
    private List<Item> item_statusList = new ArrayList<>();

    @ManyToMany(mappedBy = "spell_statusList")
    private List<Spell> spell_statusList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
