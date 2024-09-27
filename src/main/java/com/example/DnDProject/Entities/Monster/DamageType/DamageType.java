package com.example.DnDProject.Entities.Monster.DamageType;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class DamageType {

    @Id
    private int Id;
    private String name;

    @ManyToMany(mappedBy = "immunityList")
    private List<Monster> monster_imD =  new ArrayList<>();

    @ManyToMany(mappedBy = "resistanceList")
    private List<Monster> monster_res =  new ArrayList<>();

    @ManyToMany(mappedBy = "vulnerabilityList")
    private List<Monster> monster_vul =  new ArrayList<>();//Sensitivities connections

    public void setdId(int dId) {
        this.Id = dId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getdId() {
        return Id;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
