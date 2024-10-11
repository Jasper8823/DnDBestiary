package com.example.DnDProject.Entities.Monster.Location;

import com.example.DnDProject.Entities.Monster.Monster;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "name",length = 16)
    private String name;

    @ManyToMany(mappedBy = "habitats")
    private List<Monster> monster_imD = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    } // Getters and Setters
}
