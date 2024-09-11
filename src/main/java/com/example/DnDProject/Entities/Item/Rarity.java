package com.example.DnDProject.Entities.Item;



import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Rarity {
    @Id
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
    }//Getters and Setters
}
