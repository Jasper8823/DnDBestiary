package com.example.DnDProject.Entities.Item;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rarity")
public class Rarity {
    @Id
    @Column(name = "name",length = 16)
    private String name;

    @OneToMany(mappedBy = "rarity", orphanRemoval = true)
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
    }
}
