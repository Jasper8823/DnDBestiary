package com.example.DnDProject.DTOs;

import java.util.List;

public class ItemDTO {
    private String name;
    private String description;
    private boolean configurable;
    private String item_type_name;
    private String rarity_name;

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

    public String getItem_type_name() {
        return item_type_name;
    }

    public void setItem_type_name(String item_type_name) {
        this.item_type_name = item_type_name;
    }

    public String getRarity_name() {
        return rarity_name;
    }

    public void setRarity_name(String rarity_name) {
        this.rarity_name = rarity_name;
    }
}
