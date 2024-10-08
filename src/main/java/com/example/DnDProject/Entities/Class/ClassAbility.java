package com.example.DnDProject.Entities.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "class_ability")
public class ClassAbility {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "class_name")
    private CharacterClass charClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public CharacterClass getCharClass() {
        return charClass;
    }

    public void setCharClass(CharacterClass charClass) {
        this.charClass = charClass;
    }
}
