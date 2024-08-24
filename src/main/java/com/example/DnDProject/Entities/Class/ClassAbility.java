package com.example.DnDProject.Entities.Class;

import jakarta.persistence.*;

@Entity
public class ClassAbility {

    @Id
    private String name;

    private String description;
    private int level;

    // Many-to-One relationship with CharClass
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charclass_name")
    private CharClass charClass;

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public CharClass getCharClass() {
        return charClass;
    }

    public void setCharClass(CharClass charClass) {
        this.charClass = charClass;
    }
}

