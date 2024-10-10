package com.example.DnDProject.DTOs;

import java.util.List;

public class ItemDTO {
    private String name;
    private String description;
    private boolean configurable;
    private String itemTypeName;
    private String rarityName;

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

    public String getItemTypeName() {
        return itemTypeName;
    }

    public String getRarityName() {
        return rarityName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public void setRarityName(String rarityName) {
        this.rarityName = rarityName;
    }
}
