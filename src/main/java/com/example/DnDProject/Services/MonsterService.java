package com.example.DnDProject.Services;

import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository repo;
    @Autowired
    private DangerRepository dangerRepo;

    public Monster saveMonster(MonsterDTO dto){
        Monster monster = new Monster();

        monster.setName(dto.getName());
        monster.setArmor_class(dto.getArmor_class());

        // Set speed fields
        monster.setSpeed(dto.getWalk_speed());
        monster.setSwim_speed(dto.getSwim_speed());
        monster.setFly_speed(dto.getFly_speed());

        // Set HP fields
        monster.setAvg_HP((dto.getNumberofdice()*dto.getDicetype())/2+dto.getPassivebonus());
        monster.setCalc_HP(String.valueOf(dto.getNumberofdice()).concat("D").concat(String.valueOf(dto.getDicetype()))
                +" + "+dto.getPassivebonus());

        // Set monster attributes
        monster.setStrength(dto.getStrength());
        monster.setDexterity(dto.getDexterity());
        monster.setCharisma(dto.getCharisma());
        monster.setIntelligence(dto.getIntelligence());
        monster.setWisdom(dto.getWisdom());
        monster.setConstitution(dto.getBodybuild());

        // Set other fields
        monster.setPerception(dto.getPerception());
        monster.setSkill_bonus(dto.getSkill_bonus());

        // Set bonuses
        monster.setStrength_bonus(dto.getStrength_bonus());
        monster.setDexterity_bonus(dto.getDexterity_bonus());
        monster.setCharisma_bonus(dto.getCharisma_bonus());
        monster.setIntelligence_bonus(dto.getIntelligence_bonus());
        monster.setWisdom_bonus(dto.getWisdom_bonus());
        monster.setConstitution_bonus(dto.getBodybuild_bonus());

        // Set features and description
        monster.setFeatures(dto.getFeatures());
        monster.setDescription(dto.getDescription());

        // Set additional fields (nullable)
        monster.setDanger(dangerRepo.findById(dto.getDanger()).get());
//        monster.setSize(dto.getSize());
//        monster.setType(dto.getType());
//        monster.setWorldview(dto.getWorldview());
        return repo.save(monster);
    }
}

