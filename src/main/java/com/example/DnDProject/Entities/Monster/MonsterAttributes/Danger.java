package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Skill.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Danger {

    @Id
    private int degree;
    private int ExpGain;

    @OneToMany(mappedBy = "danger", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.SELECT)
    private List<Monster> monsters = new ArrayList<>();

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setExpGain(int expGain) {
        ExpGain = expGain;
    }

    public int getDegree() {
        return degree;
    }

    public int getExpGain() {
        return ExpGain;
    }//Getters and Setters
}
