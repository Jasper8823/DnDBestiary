package com.example.DnDProject.Entities.Item;

import com.example.DnDProject.Entities.Character.Character;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "configurable")
    private boolean configurable;

    @ManyToOne
    @JoinColumn(name = "itemType_name")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "rarity_name")
    private Rarity rarity;

    @ManyToMany()
    @JoinTable(
            name = "item_char",
            joinColumns = { @JoinColumn(name = "item_name") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private List<Character> item_charList = new ArrayList<>();

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
}
