package com.example.DnDProject.Entities.Monster.Location;


import com.example.DnDProject.Entities.Monster.Monster;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    private String name;

    @ManyToMany(mappedBy = "habitats")
    private List<Monster> monster_imD =  new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
