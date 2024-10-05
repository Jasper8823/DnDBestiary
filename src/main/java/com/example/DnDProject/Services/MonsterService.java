package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Monster.StatusSens.Status;
import com.example.DnDProject.Entities.Monster.Location.Location;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import com.example.DnDProject.Entities.Class.CharacterClass;

import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import com.example.DnDProject.Repositories.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository monsterRepository;

    public Monster saveMonster(MonsterDTO monsterDTO){
        Monster monster = new Monster();
        // Handle many-to-many relationships
        return monsterRepository.save(monster);
    }
}

