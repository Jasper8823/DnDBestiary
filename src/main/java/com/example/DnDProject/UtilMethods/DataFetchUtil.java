package com.example.DnDProject.UtilMethods;

import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.DTOs.ActionDTO;
import com.example.DnDProject.Exceptions.EntityNotFoundException;
import com.example.DnDProject.Exceptions.InvalidHPCalculationException;
import com.example.DnDProject.Repositories.Item.SubTypeRepository;
import com.example.DnDProject.Repositories.Monster.Action.ActionRepository;
import com.example.DnDProject.Repositories.MtoMConnections.MonsterActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataFetchUtil {

    @Autowired
    private ActionRepository actionRepo;

    @Autowired
    private MonsterActionRepository monsterActionRepo;

    @Autowired
    private SubTypeRepository subTypeRepo;

    @Cacheable(value = "entityListCache", key = "#repository.getClass().getSimpleName() + ':' + #ids")
    public <T> List<T> fetchList(List<String> ids, JpaRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }

        List<T> resultList = new ArrayList<>();
        for (String id : ids) {
            if (id != null) {
                Optional<T> optionalEntity = repository.findById(id);
                optionalEntity.ifPresent(resultList::add);
            }
        }
        return resultList;
    }


    public void fetchActionsList(List<ActionDTO> actions, Monster monster) {
        if (monster == null) {
            throw new IllegalArgumentException("Monster cannot be null.");
        }

        if (actions == null) {
            return;
        }

        for (ActionDTO actionDTO : actions) {
            if (actionDTO == null) {
                continue;
            }
            if (actionDTO.getName() == null || actionDTO.getName().isEmpty()) {
                throw new IllegalArgumentException("Action name cannot be null or empty.");
            }

            MonsterAction monsterAction = new MonsterAction();
            monsterAction.setInformation(actionDTO.getInfo());
            monsterAction.setLegendary(actionDTO.getLegend());

            // Check if the action exists; if not, create and save a new one
            if (!actionRepo.existsById(actionDTO.getName())) {
                Action action = new Action();
                action.setName(actionDTO.getName());
                actionRepo.save(action);
                monsterAction.setAction(action);
            } else {
                monsterAction.setAction(this.fetchEntity(actionRepo,actionDTO.getName()));
            }
            monsterAction.setMonster(monster);
            monsterActionRepo.save(monsterAction);
        }
    }

    @Cacheable(value = "entities", key = "#repository.getClass().getSimpleName() + ':' + #id")
    public <T> T fetchEntity(JpaRepository<T, String> repository, String id) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + id));
    }



    public Item setItemSubType(Item item, String itemTypeName, String subtypename) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (itemTypeName == null) {
            throw new IllegalArgumentException("Item type name cannot be null.");
        }
        if (subtypename == null) {
            throw new IllegalArgumentException("Subtype name cannot be null.");
        }
        if (itemTypeName.equals("weapon") || itemTypeName.equals("armor")) {
            item.setSubType(subTypeRepo.findById(subtypename).orElse(null));
        }
        return item;
    }


    public int calculateAvgHP(int numberOfDice, int dieType, int passiveBonus) {
        if (numberOfDice <= 0) {
            throw new InvalidHPCalculationException("Number of dice must be positive.");
        }
        if (dieType <= 0) {
            throw new InvalidHPCalculationException("Die type must be positive.");
        }
        if (passiveBonus < 0) {
            throw new InvalidHPCalculationException("Passive bonus must not be negative.");
        }
        return (numberOfDice * dieType) / 2 + passiveBonus;
    }

    public String formatHPCalculation(int numberOfDice, int diceType, int passiveBonus) {
        if (numberOfDice <= 0) {
            throw new InvalidHPCalculationException("Number of dice must be positive.");
        }
        if (diceType <= 0) {
            throw new InvalidHPCalculationException("Dice type must be positive.");
        }
        if (passiveBonus < 0) {
            throw new InvalidHPCalculationException("Passive bonus must not be negative.");
        }
        return numberOfDice + "D" + diceType + " + " + passiveBonus;
    }


}

