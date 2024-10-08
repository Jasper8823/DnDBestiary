package com.example.DnDProject.Entities.MtoMConnections;

import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.Monster.Monster;
import jakarta.persistence.*;

@Entity
@Table(name = "monster_action")
public class MonsterAction {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "is_legendary")
    private boolean isLegendary;

    @Column(name = "information")
    private String information;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "action_name")
    private Action action;

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public String getInformation() {
        return information;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
