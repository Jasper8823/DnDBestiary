package com.example.DnDProject.UtilMethods;

import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.DTOs.ActionDTO;
import com.example.DnDProject.Exceptions.EntityNotFoundException;
import com.example.DnDProject.Repositories.Item.SubTypeRepository;
import com.example.DnDProject.Repositories.Monster.Action.ActionRepository;
import com.example.DnDProject.Repositories.MtoMConnections.MonsterActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public  <T> List<T> fetchList(List<String> ids, JpaRepository repository) {
        List<T> resultList = new ArrayList<>();
        for (String id : ids) {
            Optional<T> optionalEntity = repository.findById(id);
            optionalEntity.ifPresent(resultList::add);
        }
        return resultList;
    }

    public void fetchActionsList(List<ActionDTO> actions, Monster monster) {
        for (ActionDTO actionDTO : actions) {
            MonsterAction monsterAction = new MonsterAction();
            monsterAction.setInformation(actionDTO.getInfo());
            monsterAction.setLegendary(actionDTO.getLegend());

            if(!actionRepo.existsById(actionDTO.getName())) {
                Action action = new Action();
                action.setName(actionDTO.getName());
                actionRepo.save(action);
                monsterAction.setAction(action);
            }else{
                monsterAction.setAction(actionRepo.findById(actionDTO.getName()).orElseThrow(() ->
                        new EntityNotFoundException("Action not found with name: " + actionDTO.getName())));
            }
            monsterAction.setMonster(monster);
            monsterActionRepo.save(monsterAction);
        }
    }

    public Item setItemSubType(Item item, String itemTypeName, String subtypename) {
        if (itemTypeName.equals("weapon") || itemTypeName.equals("armor")) {
            item.setSubType(subTypeRepo.findById(subtypename).orElse(null));
        }
        return item;
    }

    public int calculateAvgHP(int numberOfDice, int dieType, int passiveBonus) {
        return (numberOfDice * dieType) / 2 + passiveBonus;
    }

    public String formatHPCalculation(int numberOfDice, int dieType, int passiveBonus) {
        return numberOfDice + "D" + dieType + " + " + passiveBonus;
    }
}

