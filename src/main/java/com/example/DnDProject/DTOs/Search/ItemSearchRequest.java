package com.example.DnDProject.DTOs.Search;

public class ItemSearchRequest {
    private String name;
    private String rarity;
    private String type;
    private String needsAdjustment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNeedsAdjustment() {
        return needsAdjustment;
    }

    public void setNeedsAdjustment(String needsAdjustment) {
        this.needsAdjustment = needsAdjustment;
    }
}
