package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "size")
public class Size {

    @Id
    @Column(name = "name",length = 16)
    private String name;

    @OneToMany(mappedBy = "size", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Monster> monsters = new ArrayList<>();

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
