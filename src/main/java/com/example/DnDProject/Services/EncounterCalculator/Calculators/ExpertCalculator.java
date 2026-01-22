package com.example.DnDProject.Services.EncounterCalculator.Calculators;

import com.example.DnDProject.DTOs.Calculator.CharacterDTO;
import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.DTOs.Calculator.MonsterDTO;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import com.example.DnDProject.Entities.MtoMConnections.Item_DamageType;
import com.example.DnDProject.Entities.MtoMConnections.Spell_DamageType;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.Character.CharacterRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.*;

@Service
@Validated
public class ExpertCalculator {

    @Autowired
    private MonsterRepository monsterRepo;


    @Autowired
    private CharacterRepository characterRepo;

    public String calculate(GeneralCalcDTO dto) {

        if (dto == null || dto.getPlayers() == null || dto.getPlayers().isEmpty() ||
                dto.getMonsters() == null || dto.getMonsters().isEmpty()) {
            return "No players or monsters";
        }

        int easyThreshold = 0, mediumThreshold = 0, hardThreshold = 0, deadlyThreshold = 0;
        for (CharacterDTO player : dto.getPlayers()) {
            int level = player.getLevel();
            int[] thresholds = LEVEL_THRESHOLDS.get(level);
            if(player.getCount()!=null) {
                easyThreshold += player.getCount() * thresholds[0];
                mediumThreshold += player.getCount() * thresholds[1];
                hardThreshold += player.getCount() * thresholds[2];
                deadlyThreshold += player.getCount() * thresholds[3];
            }else{
                easyThreshold += thresholds[0];
                mediumThreshold += thresholds[1];
                hardThreshold += thresholds[2];
                deadlyThreshold += thresholds[3];
            }
        }

        double totalMonsterXP = 0;
        int totalMonsters = 0;

        for (MonsterDTO monsterDTO : dto.getMonsters()) {
            Monster monster = null;

            if (monsterDTO.getId() != null) {
                monster = monsterRepo.findById(monsterDTO.getId()).orElse(null);
                if (monster == null) continue;
            }else{
                double simple_mod = 1;
                int playerCount = 0;

                for (CharacterDTO player : dto.getPlayers()) {
                        int monster_index = switch (monsterDTO.getCr()) {
                            case "1/4" -> 1;
                            case "1/2" -> 2;
                            case "1/8" -> 0;
                            default -> Integer.parseInt(monsterDTO.getCr()) + 3;
                        };
                        if(player.getId()!=null) {
                            playerCount++;
                            Character character = characterRepo.findById(player.getId()).orElse(null);
                            simple_mod += simple_thresholds[character.getLevel()][monster_index] * LEVEL_THRESHOLDS.get(character.getLevel())[0]/easyThreshold;
                        }else if(player.getClassName()!=null) {
                            playerCount++;
                            simple_mod += simple_thresholds[player.getLevel()][monster_index] * LEVEL_THRESHOLDS.get(player.getLevel())[0]/easyThreshold;
                        }else{
                            playerCount += player.getCount();
                            simple_mod += player.getCount() * simple_thresholds[player.getLevel()][monster_index] * LEVEL_THRESHOLDS.get(player.getLevel())[0]/easyThreshold;
                        }
                }
                int xp = CR_XP.getOrDefault(monsterDTO.getCr(), 0);
                System.out.println("b-b "+xp+" "+getEncounterMultiplier(monsterDTO.getCount(),playerCount)+" "+simple_mod);
                double temp = 0;
                temp = xp * getEncounterMultiplier(monsterDTO.getCount(),playerCount) / simple_mod;
                if(temp>xp){
                    temp = xp;
                }
                totalMonsterXP += temp;
                System.out.println(totalMonsterXP);
                totalMonsters += monsterDTO.getCount();

                continue;
            }

            int count = 1;
            int baseXP = 0;

            double modifier = 1.0;

            if (dto.getTopography() != null) {
                Topography top = new Topography();
                top.setName(dto.getTopography());

                if (monster.getTopographyAdvList().contains(top)) {
                    modifier *= 0.66;
                }
                if (monster.getTopographyWeakList().contains(top)) {
                    modifier *= 1.5;
                }
            }
            double totalItemEfficiency = 0;
            int characterCount = 0;
            int supportCount = 0;


            if (monster != null) {

                for (CharacterDTO player : dto.getPlayers()) {
                    Character character;
                    if (player.getId() != null) {
                        character = characterRepo.findById(player.getId()).orElse(null);
                        if (character == null) continue;
                    } else{
                        double simple_mod = 1;
                        int monster_index = switch (monster.getDanger().getDegree()) {
                            case 108 -> 1;
                            case 104 -> 2;
                            case 102 -> 0;
                            default -> monster.getDanger().getDegree() + 3;
                        };
                        simple_mod = simple_thresholds[player.getLevel()][monster_index];
                        System.out.println("b-c "+CR_XP.getOrDefault(String.valueOf(monster.getDanger().getDegree()), 0)+" "+getEncounterMultiplier(monsterDTO.getCount(),dto.getPlayers().size())+" "+simple_mod);
                        double temp = 0;
                        temp = CR_XP.getOrDefault(String.valueOf(monster.getDanger().getDegree()), 0) * getEncounterMultiplier(monsterDTO.getCount(),dto.getPlayers().size()) / simple_mod;
                        if(temp>CR_XP.getOrDefault(String.valueOf(monster.getDanger().getDegree()),0)){
                            temp = CR_XP.getOrDefault(String.valueOf(monster.getDanger().getDegree()),0);
                        }
                        totalMonsterXP += temp;
                        System.out.println(totalMonsterXP);
                        if(player.getClassName()!=null){
                            for(CharacterClass charClass : monster.getClassAdvList()){
                                if (charClass.getName().equals(player.getClassName())) {
                                    modifier *= 1.5;
                                }
                            }

                            for(CharacterClass charClass : monster.getClassWeakList()){
                                if (charClass.getName().equals(player.getClassName())) {
                                    modifier *= 0.66;
                                }
                            }
                        }
                        continue;
                    }

                    if (player.getId() != null) {
                        character = characterRepo.findById(player.getId()).orElse(null);
                        if (character == null) continue;
                    } else {
                        character = null;
                    }
                    String className = character.getCharClass().getName().toLowerCase();

                    boolean isSupportClass =
                            className.equals("bard") ||
                                    className.equals("cleric") ||
                                    className.equals("paladin");
                    if (isSupportClass) supportCount++;

                    String[] dexterityWeapons = {
                            "dagger", "rapier", "short sword", "scimitar",
                            "whip", "dart", "light crossbow", "short bow",
                            "sling", "hand crossbow",
                            "heavy crossbow", "long bow",
                            "blowgun", "net"
                    };

                    double bestEffectiveDamage = 0;


                    for (Item item : character.getItem_charList()){
                        if (!item.getItemType().getName().equals("weapon")) continue;

                        String itemName = item.getSubType().getName().toLowerCase();
                        boolean isDexWeapon = Arrays.asList(dexterityWeapons).contains(itemName);
                        boolean isProficient = character.getCharClass().getSubtype_classList().stream()
                                .anyMatch(cp -> cp.getName().equals(item.getSubType().getName())) ||
                                character.getRace().getRaceProfList().stream()
                                        .anyMatch(rp -> rp.getName().equals(item.getSubType().getName()));

                        int abilityModifier = (isDexWeapon && character.getDexterity() >= character.getStrength()) ?
                                (character.getDexterity()-10) / 2 :
                                (character.getStrength()-10) / 2;

                        int attackModifier = abilityModifier + (isProficient ? character.getProficiencyBonus() : 0);

                        int neededRoll = monster.getArmor_class() - attackModifier;
                        double hitChance = Math.max(0, Math.min(1, (21.0 - neededRoll) / 20.0));

                        for (Item_DamageType idt : item.getItemDamageTypeList()) {
                            if (monster.getImmunityList().contains(idt.getDamageType())) continue;

                            String[] parts = idt.getDamageDice().split("[d+]");
                            double avgDamage = DataFetchUtil.calculateAvgHP(
                                    Integer.parseInt(parts[0]),
                                    Integer.parseInt(parts[1]),
                                    Integer.parseInt(parts[2])
                            );

                            if (monster.getVulnerabilityList().contains(idt.getDamageType())) avgDamage *= 2;
                            if (monster.getResistanceList().contains(idt.getDamageType())) avgDamage /= 2;

                            boolean hasExtraAttack =
                                    (className.equals("fighter") ||
                                            className.equals("paladin") ||
                                            className.equals("ranger") ||
                                            className.equals("barbarian") ||
                                            className.equals("monk"))
                                            && character.getLevel() >= 5;

                            if (hasExtraAttack) {
                                avgDamage*=2;
                            }

                            double effectiveDamage = avgDamage * hitChance;

                            double efficiencyScore;
                            if (effectiveDamage <= monster.getAvg_HP()) {
                                efficiencyScore = effectiveDamage / monster.getAvg_HP();
                            } else {
                                efficiencyScore = monster.getAvg_HP() / effectiveDamage;
                            }

                            if (efficiencyScore > bestEffectiveDamage) {
                                bestEffectiveDamage = efficiencyScore;
                            }
                        }
                    }

                    double bestSpellEfficiency = 0;

                    for (Spell spell : character.getSpell_charList()) {
                        int spellAttackModifier = 0;

                        switch (className) {
                            case "wizard":
                                spellAttackModifier = getAbilityMod(character.getIntelligence());
                                break;
                            case "sorcerer":
                            case "bard":
                            case "warlock":
                            case "paladin":
                                spellAttackModifier = getAbilityMod(character.getCharisma());
                                break;
                            case "druid":
                            case "cleric":
                            case "ranger":
                                spellAttackModifier = getAbilityMod(character.getWisdom());
                                break;
                            default:
                                spellAttackModifier = 0;
                        }


                        int neededRoll = monster.getArmor_class() - spellAttackModifier;
                        double hitChance = Math.max(0, Math.min(1, (21.0 - neededRoll) / 20.0));

                        for (Spell_DamageType sdt : spell.getSpellDamageTypeList()) {

                            if (monster.getImmunityList().contains(sdt.getDamageType())) continue;

                            String[] parts = sdt.getDamageDice().split("[d+]");
                            double avgDamage = DataFetchUtil.calculateAvgHP(
                                    Integer.parseInt(parts[0]),
                                    Integer.parseInt(parts[1]),
                                    Integer.parseInt(parts[2])
                            );

                            if (monster.getVulnerabilityList().contains(sdt.getDamageType())) avgDamage *= 2;
                            if (monster.getResistanceList().contains(sdt.getDamageType())) avgDamage /= 2;
                            double effectiveDamage = avgDamage * hitChance;

                            double efficiencyScore;
                            if (effectiveDamage <= monster.getAvg_HP()) {
                                efficiencyScore = effectiveDamage / monster.getAvg_HP();
                            } else {
                                efficiencyScore = monster.getAvg_HP() / effectiveDamage;
                            }

                            if (efficiencyScore > bestSpellEfficiency) {
                                bestSpellEfficiency = efficiencyScore;
                            }
                        }
                    }

                    double bestOverallEfficiency = Math.max(bestEffectiveDamage, bestSpellEfficiency);


                    double efficiencyModifier = Math.min(
                            Math.max(bestOverallEfficiency * 2, 0),
                            2
                    );

                    totalItemEfficiency += efficiencyModifier;
                    characterCount++;
                }
            }
            double avgCenteredEfficiency = totalItemEfficiency / characterCount;

            double supportModifier = 1.0 + Math.min(0.1 * supportCount, 0.3);
            System.out.println(1+" "+ totalMonsterXP);
            baseXP = CR_XP.getOrDefault(String.valueOf(monster.getDanger().getDegree()),0);

            System.out.println("c-c"+baseXP+" "+count+" "+modifier+" "+avgCenteredEfficiency+" "+supportModifier);
            if(avgCenteredEfficiency==0){
                avgCenteredEfficiency=0.1;
            }
            double temp = baseXP * count * modifier / (avgCenteredEfficiency * supportModifier);
            if(temp > baseXP){
                temp=baseXP;
            }
            totalMonsterXP += temp;
            System.out.println(totalMonsterXP);
            totalMonsters += monsterDTO.getCount();
        }

        double multiplier = getEncounterMultiplier(
                totalMonsters,
                dto.getPlayers().size()
        );

        double finalXP = totalMonsterXP * multiplier;

        System.out.println("Total XP: " + totalMonsterXP);

        if (finalXP < easyThreshold) {
            return "Very Simple";
        } else if (finalXP < mediumThreshold) {
            return "Easy";
        } else if (finalXP < hardThreshold) {
            return "Medium";
        } else if (finalXP < deadlyThreshold) {
            return "Hard";
        } else {
            return "Deadly";
        }
    }
    int getAbilityMod(int stat) {
        return (stat - 10) / 2;
    }
}