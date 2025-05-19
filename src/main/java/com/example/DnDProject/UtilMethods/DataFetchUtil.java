package com.example.DnDProject.UtilMethods;

import com.example.DnDProject.DTOs.DamageTypeDTO;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.MtoMConnections.Item_DamageType;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.DTOs.ActionDTO;
import com.example.DnDProject.Entities.MtoMConnections.Spell_DamageType;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Exceptions.EntityNotFoundException;
import com.example.DnDProject.Exceptions.InvalidHPCalculationException;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import com.example.DnDProject.Repositories.Item.SubTypeRepository;
import com.example.DnDProject.Repositories.Monster.Action.ActionRepository;
import com.example.DnDProject.Repositories.Monster.DamageType.DamageTypeRepository;
import com.example.DnDProject.Repositories.MtoMConnections.Item_DamageTypeRepository;
import com.example.DnDProject.Repositories.MtoMConnections.MonsterActionRepository;
import com.example.DnDProject.Repositories.MtoMConnections.Spell_DamageTypeRepository;
import com.example.DnDProject.Repositories.Spell.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataFetchUtil {

    @Autowired
    private ActionRepository actionRepo;
    @Autowired
    private MonsterActionRepository monsterActionRepo;

    @Autowired
    private SubTypeRepository subTypeRepo;

    @Autowired
    private DamageTypeRepository damageTypeRepo;

    @Autowired
    private Item_DamageTypeRepository itemDamTypeRepo;
    @Autowired
    private Spell_DamageTypeRepository spellDamTypeRepo;

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

    public static <T> List<String> extractNames(List<T> list, Function<T, String> nameExtractor) {
        return list.stream()
                .map(nameExtractor)
                .collect(Collectors.toList());
    }



    public void fetchDamageTypesList(List<DamageTypeDTO> damageTypes, Item item, Spell spell) {

        if (damageTypes == null) {
            return;
        }

        if (item == null && spell == null) {
            throw new IllegalArgumentException("Both objects cannot be null.");
        } else if (item != null && spell != null) {
            throw new IllegalArgumentException("At least one object should be null.");
        } else{
            for (DamageTypeDTO damageTypeDTO : damageTypes) {
                if (damageTypeDTO == null) {
                    continue;
                }
                if (damageTypeDTO.getName() == null || damageTypeDTO.getName().isEmpty()) {
                    throw new IllegalArgumentException("DamageType name cannot be null or empty.");
                }

                if(item != null){
                    Item_DamageType itemDamageType = new Item_DamageType();
                    itemDamageType.setDamageDice(damageTypeDTO.getDamage_dice());

                    // Check if the damageType exists; if not, create and save a new one
                    if (!damageTypeRepo.existsById(damageTypeDTO.getName())) {
                        DamageType damageType = new DamageType();
                        damageType.setName(damageTypeDTO.getName());
                        damageTypeRepo.save(damageType);
                        itemDamageType.setDamageType(damageTypeRepo.findById(damageTypeDTO.getName()).
                                orElseThrow(() -> new IllegalStateException("DamageType should have been saved but was not found")));
                    } else {
                        itemDamageType.setDamageType(this.fetchEntity(damageTypeRepo,damageTypeDTO.getName()));
                    }
                    itemDamageType.setItem(item);
                    System.out.println(itemDamageType.getDamageType().getName() + " " + itemDamageType.getItem().getName());

                    itemDamTypeRepo.save(itemDamageType);
                } else {
                    Spell_DamageType spellDamageType = new Spell_DamageType();
                    spellDamageType.setDamageDice(damageTypeDTO.getDamage_dice());

                    // Check if the damageType exists; if not, create and save a new one
                    if (!damageTypeRepo.existsById(damageTypeDTO.getName())) {
                        DamageType damageType = new DamageType();
                        damageType.setName(damageTypeDTO.getName());
                        damageTypeRepo.save(damageType);
                        spellDamageType.setDamageType(damageTypeRepo.findById(damageTypeDTO.getName()).
                                orElseThrow(() -> new IllegalStateException("Damage type should have been saved but was not found")));
                    } else {
                        spellDamageType.setDamageType(this.fetchEntity(damageTypeRepo,damageTypeDTO.getName()));
                    }
                    spellDamageType.setSpell(spell);
                    spellDamTypeRepo.save(spellDamageType);
                }
            }
        }



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
                monsterAction.setAction(actionRepo.findById(actionDTO.getName()).
                        orElseThrow(() -> new IllegalStateException("Action should have been saved but was not found")));
            } else {
                monsterAction.setAction(this.fetchEntity(actionRepo,actionDTO.getName()));
            }
            monsterAction.setMonster(monster);
            monsterActionRepo.save(monsterAction);
        }
    }


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