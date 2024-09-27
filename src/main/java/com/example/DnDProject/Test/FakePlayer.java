package com.example.DnDProject.Test;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Player")
public class FakePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer level;
    private String name;

    // Many Players can have many Pillows
    @ManyToMany
    @JoinTable(
            name = "player_pillow", // Name of the join table
            joinColumns = @JoinColumn(name = "player_id"), // Foreign key for Player
            inverseJoinColumns = @JoinColumn(name = "pillow_id") // Foreign key for Pillow
    )
    private List<Pillow> pillows = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pillow> getPillows() {
        return pillows;
    }

    public void setPillows(List<Pillow> pillows) {
        this.pillows = pillows;
    }
}
