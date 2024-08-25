package com.example.DnDProject.Entities.Monster.StatusSens;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Status {

    @Id
    private int Id;

    @OneToMany(mappedBy = "status", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<ImmunityStatus> immunityStatusList = new ArrayList<>();
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }//Getters and Setters
}
