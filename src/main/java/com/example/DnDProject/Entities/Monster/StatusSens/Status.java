package com.example.DnDProject.Entities.Monster.StatusSens;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
public class Status {

    @Id
    private String name;

    @ManyToMany(mappedBy = "immunityStatusList")
    private List<Monster> monster_imS = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
