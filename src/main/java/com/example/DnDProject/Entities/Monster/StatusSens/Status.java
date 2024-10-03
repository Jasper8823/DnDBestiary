package com.example.DnDProject.Entities.Monster.StatusSens;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
public class Status {

    @Id
    private int Id;

    @ManyToMany(mappedBy = "immunityStatusList")
    private List<Monster> monster_imS =  new ArrayList<>();
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }//Getters and Setters

}
