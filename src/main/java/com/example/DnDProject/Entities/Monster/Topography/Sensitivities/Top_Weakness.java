package com.example.DnDProject.Entities.Monster.Topography.Sensitivities;

import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
public class Top_Weakness {

    @Id //describes what type of terrain the monster will have an disadvantage
        //example: long-range units will have an easier fighting in narrow corridors
    private int id;
    @ManyToOne
    @JoinColumn(name = "monster_id")
    @LazyToOne(LazyToOneOption.PROXY)
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "topography_name")
    @LazyToOne(LazyToOneOption.PROXY)
    private Topography topography;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }//Getters and Setters
}
