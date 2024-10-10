package com.example.DnDProject.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    private String name;
    private String description;
    private boolean configurable;
    private String item_type_name;
    private String rarity_name;
    private String subtype_name;

    private List<String> damageType_names = new ArrayList<>();
    private List<String> status_names = new ArrayList<>();

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

    public String getSubtype_name() {
        return subtype_name;
    }

    public void setSubtype_name(String subtype_name) {
        this.subtype_name = subtype_name;
    }

    public List<String> getDamageType_names() {
        return damageType_names;
    }

    public void setDamageType_names(List<String> damageType_names) {
        this.damageType_names = damageType_names;
    }

    public List<String> getStatus_names() {
        return status_names;
    }

    public void setStatus_names(List<String> status_names) {
        this.status_names = status_names;
    }
}
