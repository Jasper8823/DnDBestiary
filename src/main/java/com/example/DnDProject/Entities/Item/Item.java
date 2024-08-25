package com.example.DnDProject.Entities.Item;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
public class Item {
    @Id
    private String name;
    private String description;
    private boolean configurable;

    @ManyToOne
    @JoinColumn(name = "itemType_name")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "rarity_name")
    private Rarity rarity;

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
    }//Getters and Setters
}
