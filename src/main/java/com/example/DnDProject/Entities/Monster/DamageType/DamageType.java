package com.example.DnDProject.Entities.Monster.DamageType;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "damage_type")
public class DamageType {

    @Id
    private String name;

    @ManyToMany(mappedBy = "immunityList")
    private List<Monster> monster_imD =  new ArrayList<>();

    @ManyToMany(mappedBy = "resistanceList")
    private List<Monster> monster_res =  new ArrayList<>();

    @ManyToMany(mappedBy = "vulnerabilityList")
    private List<Monster> monster_vul =  new ArrayList<>();//Sensitivities connections

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
