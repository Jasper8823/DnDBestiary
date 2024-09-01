package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import com.example.DnDProject.Entities.Monster.Monster;
import javax.persistence.*;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Type {

    @Id
    private String name;
    @OneToMany(mappedBy = "type", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Monster> monsters = new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
