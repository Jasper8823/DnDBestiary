package com.example.DnDProject.Entities.Monster.StatusSens;

import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
public class ImmunityStatus {

    @Id //monsters may be immune to certain conditions
        //example: skeleton will not be affected by poison
    private int Id;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    @LazyToOne(LazyToOneOption.PROXY)
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "status_name")
    @LazyToOne(LazyToOneOption.PROXY)
    private Status status;
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }//Getters and Setters
}
