package com.example.DnDProject.Entities.Item;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Monster.Status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "description",length = 4096)
    private String description;

    @Column(name = "configurable")
    private boolean configurable;

    //One-to-many connections
    @ManyToOne
    @JoinColumn(name = "item_type_name")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "sub_type_name")
    private SubType subType;

    @ManyToOne
    @JoinColumn(name = "rarity_name")
    private Rarity rarity;

    //Many-to-many connections
    @ManyToMany()
    @JoinTable(
            name = "item_char",
            joinColumns = { @JoinColumn(name = "item_name") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> item_charList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "item_dam_type",
            joinColumns = { @JoinColumn(name = "item_name") },
            inverseJoinColumns = { @JoinColumn(name = "dam_type_name") }
    )
    private List<DamageType> item_damTypeList = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "item_status",
            joinColumns = { @JoinColumn(name = "item_name") },
            inverseJoinColumns = { @JoinColumn(name = "status_name") }
    )
    private List<Status> item_statusList = new ArrayList<>();


    //Getters and Setters
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfigurable() {
        return configurable;
    }

    public void setConfigurable(boolean configurable) {
        this.configurable = configurable;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public List<Character> getItem_charList() {
        return item_charList;
    }

    public void setItem_charList(List<Character> item_charList) {
        this.item_charList = item_charList;
    }

    public List<DamageType> getItem_damTypeList() {
        return item_damTypeList;
    }

    public void setItem_damTypeList(List<DamageType> item_damTypeList) {
        this.item_damTypeList = item_damTypeList;
    }

    public List<Status> getItem_statusList() {
        return item_statusList;
    }

    public void setItem_statusList(List<Status> item_statusList) {
        this.item_statusList = item_statusList;
    }
}
