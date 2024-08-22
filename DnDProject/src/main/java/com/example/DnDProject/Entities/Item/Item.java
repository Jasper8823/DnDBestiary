package com.example.DnDProject.Entities.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Item {
    @Id
    private String name;
    private String description;
    private boolean configurable;

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
