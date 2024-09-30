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

        monster.setName(monsterDTO.getName());
        monster.setArmor_class(monsterDTO.getArmor_class());
        monster.setSpeed(monsterDTO.getSpeed());
        monster.setSwim_speed(monsterDTO.getSwim_speed());
        monster.setFly_speed(monsterDTO.getFly_speed());
        monster.setAvg_HP(monsterDTO.getAvg_HP());
        monster.setCalc_HP(monsterDTO.getCalc_HP());
        monster.setStrength(monsterDTO.getStrength());
        monster.setDexterity(monsterDTO.getDexterity());
        monster.setCharisma(monsterDTO.getCharisma());
        monster.setIntelligence(monsterDTO.getIntelligence());
        monster.setWisdom(monsterDTO.getWisdom());
        monster.setConstitution(monsterDTO.getConstitution());
        monster.setPerception(monsterDTO.getPerception());
        monster.setSkill_bonus(monsterDTO.getSkill_bonus());
        monster.setStrength_bonus(monsterDTO.getStrength_bonus());
        monster.setDexterity_bonus(monsterDTO.getDexterity_bonus());
        monster.setCharisma_bonus(monsterDTO.getCharisma_bonus());
        monster.setIntelligence_bonus(monsterDTO.getIntelligence_bonus());
        monster.setWisdom_bonus(monsterDTO.getWisdom_bonus());
        monster.setConstitution_bonus(monsterDTO.getConstitution_bonus());
        monster.setFeatures(monsterDTO.getFeatures());
        monster.setDescription(monsterDTO.getDescription());

        // Set nullable attributes
        monster.setDanger(monsterDTO.getDanger());
        monster.setSize(monsterDTO.getSize());
        monster.setType(monsterDTO.getType());
        monster.setWorldview(monsterDTO.getWorldview());

        // Handle many-to-many relationships
        List<DamageType> immunityList = monsterDTO.getImmunityList();
        if (immunityList != null) {
            monster.getImmunityList().addAll(immunityList);
        }

        List<DamageType> resistanceList = monsterDTO.getResistanceList();
        if (resistanceList != null) {
            monster.getResistanceList().addAll(resistanceList);
        }

        List<DamageType> vulnerabilityList = monsterDTO.getVulnerabilityList();
        if (vulnerabilityList != null) {
            monster.getVulnerabilityList().addAll(vulnerabilityList);
        }

        List<Status> immunityStatusList = monsterDTO.getImmunityStatusList();
        if (immunityStatusList != null) {
            monster.getImmunityStatusList().addAll(immunityStatusList);
        }

        List<Location> habitats = monsterDTO.getHabitats();
        if (habitats != null) {
            monster.getHabitats().addAll(habitats);
        }

        List<CharacterClass> classAdvList = monsterDTO.getClassAdvList();
        if (classAdvList != null) {
            monster.getClassAdvList().addAll(classAdvList);
        }

        List<CharacterClass> classWeakList = monsterDTO.getClassWeakList();
        if (classWeakList != null) {
            monster.getClassWeakList().addAll(classWeakList);
        }

        List<MonsterAction> monsterActions = monsterDTO.getMonsterActions();
        if (monsterActions != null) {
            monster.getMonsterActions().addAll(monsterActions);
        }

        List<Topography> topographyWeakList = monsterDTO.getTopographyWeakList();
        if (topographyWeakList != null) {
            monster.getTopographyWeakList().addAll(topographyWeakList);
        }

        List<Topography> topographyAdvList = monsterDTO.getTopographyAdvList();
        if (topographyAdvList != null) {
            monster.getTopographyAdvList().addAll(topographyAdvList);
        }

        return monsterRepository.save(monster);
    }
}

