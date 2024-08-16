package com.example.DnDProject.Entities.Monster.MonsterAttributes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Danger {

    @Id
    private int degree;

    private int ExpGain;

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
    }
}
