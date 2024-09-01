package com.example.DnDProject.Entities.Monster.Topography;

import com.example.DnDProject.Entities.Monster.Monster;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topography {

    @Id //describes the type of terrain in which the battle will take place
    private String name;


    @ManyToMany(mappedBy = "topographyAdvList")
    private List<Monster> monster_topAdv =  new ArrayList<>();
    @ManyToMany(mappedBy = "topographyWeakList")
    private List<Monster> monster_topWeak =  new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
