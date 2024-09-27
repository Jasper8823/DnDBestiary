package com.example.DnDProject.Test;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pillow")
public class Pillow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    // Many Pillows can belong to many Players
    @ManyToMany(mappedBy = "pillows") // Reference to the field in FakePlayer
    private List<FakePlayer> players = new ArrayList<>();

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FakePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<FakePlayer> players) {
        this.players = players;
    }
}
