package com.example.DnDProject.Entities.Monster.Location;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    private String name;

    @ManyToMany(mappedBy = "habitats")
    private List<Monster> monster_imD =  new ArrayList<>();

    private List<Habitat> habitats = new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
