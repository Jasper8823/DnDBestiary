package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type", orphanRemoval = true)
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
