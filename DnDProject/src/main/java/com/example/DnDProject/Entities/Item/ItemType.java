package com.example.DnDProject.Entities.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ItemType {
    @Id
    private String name;
}