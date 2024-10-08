package com.example.DnDProject.Services;

import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Monster.Action.ActionRepository;
import com.example.DnDProject.Repositories.Monster.DamageType.DamageTypeRepository;
import com.example.DnDProject.Repositories.Monster.Location.LocationRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.DangerRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.SizeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.TypeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.WorldviewRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.Repositories.Monster.StatusSens.StatusRepository;
import com.example.DnDProject.Repositories.Monster.Topography.TopographyRepository;
import com.example.DnDProject.Repositories.MtoMConnections.MonsterActionRepository;
import com.example.DnDProject.Repositories.MtoMConnections.RaceAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository repo;

    @Autowired
    private DangerRepository dangerRepo;
    @Autowired
    private SizeRepository sizeRepo;
    @Autowired
    private TypeRepository typeRepo;
    @Autowired
    private WorldviewRepository worldviewRepo;


    @Autowired
    private ActionRepository actionRepo;


    @Autowired
    private StatusRepository statusRepo;
    @Autowired
    private DamageTypeRepository damageTypeRepo;


    @Autowired
    private LocationRepository locationRepo;
    @Autowired
    private TopographyRepository topographyRepo;

    @Autowired
    private MonsterActionRepository monsterActionRepo;

    @Autowired
    private CharacterClassRepository classRepo;


    public Monster saveMonster(MonsterDTO dto) throws Throwable {
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

        monster.setDanger(dangerRepo.findById(dto.getDanger()).get());
        monster.setType(typeRepo.findById(dto.getType()).get());
        monster.setSize(sizeRepo.findById(dto.getSize()).get());
        monster.setWorldview(worldviewRepo.findById(dto.getWorldview()).get());

        monster.setResistanceList(fetchList(dto.getResistanceList(),damageTypeRepo));
        monster.setVulnerabilityList(fetchList(dto.getVulnerabilityList(),damageTypeRepo));
        monster.setImmunityList(fetchList(dto.getImmunityList(),damageTypeRepo));

        monster.setImmunityStatusList(fetchList(dto.getImmunityStatusList(),statusRepo));

        monster.setHabitats(fetchList(dto.getHabitats(),locationRepo));

        monster.setTopographyAdvList(fetchList(dto.getTopographyAdvList(),topographyRepo));
        monster.setTopographyWeakList(fetchList(dto.getTopographyWeakList(),topographyRepo));

        monster.setClassAdvList(fetchList(dto.getClassAdvList(),classRepo));
        monster.setClassWeakList(fetchList(dto.getClassWeakList(),classRepo));
        return repo.save(monster);
    }

    private <T> List<T> fetchList(List<String> ids, JpaRepository repository) {
        List<T> resultList = new ArrayList<>();
        for (String id : ids) {
            Optional<T> optionalEntity = repository.findById(id);
            optionalEntity.ifPresent(resultList::add);
        }
        return resultList;
    }

}

