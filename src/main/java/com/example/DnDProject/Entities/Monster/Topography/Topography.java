package com.example.DnDProject.Entities.Monster.Topography;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "topography")
public class Topography {

    @Id
    @Column(name = "name",length = 32) //describes the type of terrain in which the battle will take place
    private String name;


    //Many-to-many connections
    @ManyToMany(mappedBy = "topographyAdvList")
    private List<Monster> monster_topAdv = new ArrayList<>();

    @ManyToMany(mappedBy = "topographyWeakList")
    private List<Monster> monster_topWeak = new ArrayList<>();


    //Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
