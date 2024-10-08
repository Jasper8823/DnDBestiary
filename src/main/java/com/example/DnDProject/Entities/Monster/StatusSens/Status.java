package com.example.DnDProject.Entities.Monster.StatusSens;

import com.example.DnDProject.Entities.Monster.Monster;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "name")
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
