package com.example.DnDProject.Entities.Monster.DamageType;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DamageType {

    @Id
    private int Id;
    private String name;

    @ManyToMany(mappedBy = "immunityDamageList")
    private List<Monster> monster_imD =  new ArrayList<>();

    @ManyToMany(mappedBy = "resistanceList")
    private List<Monster> monster_res =  new ArrayList<>();

    @ManyToMany(mappedBy = "vulnerabilityDamageList")
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
