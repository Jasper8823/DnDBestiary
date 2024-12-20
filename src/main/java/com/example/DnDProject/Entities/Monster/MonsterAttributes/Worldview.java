package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "worldview")
public class Worldview {

    @Id
    @Column(name = "name",length = 16)
    private String name;
    @OneToMany(mappedBy = "worldview", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Monster> monsters = new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
