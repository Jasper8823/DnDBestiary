package com.example.DnDProject.Entities.Monster.Topography;

import com.example.DnDProject.Entities.Monster.StatusSens.ImmunityStatus;
import com.example.DnDProject.Entities.Monster.Topography.Sensitivities.Top_Advantage;
import com.example.DnDProject.Entities.Monster.Topography.Sensitivities.Top_Weakness;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Topography {

    @Id //describes the type of terrain in which the battle will take place
    private String name;

    @OneToMany(mappedBy = "topography", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Top_Advantage> topAdvantages = new ArrayList<>();

    @OneToMany(mappedBy = "topography", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Top_Weakness> topWeaknesses = new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
