package com.example.DnDProject.Entities.Item;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemType {
    @Id
    private String name;
    @OneToMany(mappedBy = "itemType", orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }//Getters and Setters
}