package com.example.DnDProject.Entities.Monster.Action;

import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Action {

    @Id
    private String name;

    @OneToMany(mappedBy = "action", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<MonsterAction> monsterActions = new ArrayList<>();
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }//Getters and Setters
}
