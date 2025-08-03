package com.example.DnDProject.Services.EncounterCalculator;

import com.example.DnDProject.DTOs.CalcDTO;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Monster.Status.Status;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import com.example.DnDProject.Entities.MtoMConnections.Item_DamageType;
import com.example.DnDProject.Entities.MtoMConnections.Spell_DamageType;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.*;


@Service
@Validated
public class CombatCalculator {
    public String calculateSimpleDifficulty(CalcDTO dto) {
        if (dto == null || dto.getPlayers() == null || dto.getPlayers().isEmpty() ||
                dto.getMonsters() == null || dto.getMonsters().isEmpty()) {
            return "No players or monsters";
        }

        int easyThreshold = 0;
        int mediumThreshold = 0;
        int hardThreshold = 0;
        int deadlyThreshold = 0;
        for (CalcDTO.PlayerGroup group : dto.getPlayers()) {
            int[] thresholds = LEVEL_THRESHOLDS.get(group.getLevel());
            easyThreshold+=thresholds[0]*group.getCount();
            mediumThreshold+=thresholds[1]*group.getCount();
            hardThreshold+=thresholds[2]*group.getCount();
            deadlyThreshold+=thresholds[3]*group.getCount();
        }

        int totalMonsterXP = 0;
        int totalMonsters = 0;
        for (CalcDTO.MonsterGroup group : dto.getMonsters()) {
            int xp = CR_XP.get(group.getCr());
            totalMonsterXP += xp*group.getCount();
            totalMonsters += group.getCount();
        }

        double multiplier =
                getEncounterMultiplier(totalMonsters,
                        dto.getPlayers().stream().mapToInt(CalcDTO.PlayerGroup::getCount).sum());

        double finalXP = totalMonsterXP*multiplier;

        if (finalXP<easyThreshold) {
            return "Very Simple";
        } else if (finalXP<mediumThreshold) {
            return "Easy";
        } else if (finalXP<hardThreshold) {
            return "Medium";
        } else if (finalXP<deadlyThreshold) {
            return "Hard";
        } else {
            return "Deadly";
        }
    }




    public static String calculateDifficulty(List<Character> characters, List<Monster> monsters,Topography topography) {
        if (characters == null || monsters == null || characters.isEmpty() || monsters.isEmpty()) {
            return "No monsters or characters";
        }

        double totalMonsterXP = 0;
        for (Monster monster : monsters) {
            totalMonsterXP += monster.getDanger().getExpGain();
        }

        double totalCharEffectiveXP = 0;
        for (Character character : characters) {
            for (Monster monster : monsters) {
                double efficiency = calculateEfficiency(character, monster,topography);
                totalCharEffectiveXP += monster.getDanger().getExpGain() * efficiency;
            }
        }

        double ratio = totalCharEffectiveXP / totalMonsterXP;
        System.out.println("----------------------------------------------------");
        System.out.println("Total Character Effective XP: " + totalCharEffectiveXP);
        System.out.println("Total Monster XP: " + totalMonsterXP);
        System.out.println("Party/Monster XP Ratio: " + ratio);

        if (ratio > 1.5) {
            return "Easy";
        } else if (ratio > 1.0) {
            return "Medium";
        } else if (ratio > 0.7) {
            return "Hard";
        } else {
            return "Deadly";
        }
    }


    public static double calculateEfficiency(Character character, Monster monster, Topography topography) {
        double efficiency = 1;
        double monsterHPvar = monster.getAvg_HP() * 1.25;

        if (monster.getClassAdvList() != null) {
            if (monster.getClassAdvList().contains(character.getCharClass())) {
                efficiency /= 1.2;
            }
        }
        if (monster.getClassWeakList() != null) {
            if (monster.getClassWeakList().contains(character.getCharClass())) {
                efficiency *= 1.2;
            }
        }

        if (monster.getTopographyAdvList() != null) {
            if (monster.getTopographyAdvList().contains(topography)) {
                efficiency /= 1.15;
            }
        }
        if (monster.getTopographyWeakList() != null) {
            if (monster.getTopographyWeakList().contains(topography)) {
                efficiency *= 1.15;
            }
        }

        if (character.getItem_charList() != null) {

            String[] dexterityWeapons = {
                    "dagger", "rapier", "short sword", "scimitar",
                    "whip", "dart", "light crossbow", "short bow",
                    "sling", "hand crossbow",
                    "heavy crossbow", "long bow",
                    "blowgun", "net"
            };
            double bestScore = 0;

            for (Item item : character.getItem_charList()) {
                if(item.getItemType().getName().equals("weapon")) {

                    String itemName = item.getSubType().getName().toLowerCase();
                    boolean isDexWeapon = Arrays.asList(dexterityWeapons).contains(itemName);

                    boolean isProficient =
                            character.getCharClass().getSubtype_classList().stream()
                                    .anyMatch(cp -> cp.getName().equals(item.getSubType().getName())) ||
                                    character.getRace().getRaceProfList().stream()
                                            .anyMatch(rp -> rp.getName().equals(item.getSubType().getName()));


                    int abilityModifier;
                    if (isDexWeapon && character.getDexterity() >= character.getStrength()) {
                        abilityModifier = (character.getDexterity()-10)/2;
                    } else {
                        abilityModifier = (character.getStrength()-10)/2;
                    }

                    int attackModifier = abilityModifier;
                    if (isProficient) {
                        attackModifier += character.getProficiencyBonus();
                    }

                    int neededRoll = monster.getArmor_class()-attackModifier;
                    double hitChance = Math.max(0, Math.min(1, (21-neededRoll)/20.0));

                    double itemBestScore = 0;

                    for (Item_DamageType idt : item.getItemDamageTypeList()) {
                        String[] parts = idt.getDamageDice().split("[d+]");
                        double score = DataFetchUtil.calculateAvgHP(Integer.parseInt(parts[0]),
                                Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));


                        if (monster.getImmunityList().contains(idt.getDamageType())) {
                            break;
                        }
                        if (monster.getVulnerabilityList().contains(idt.getDamageType())) {
                            score *= 2;
                        }
                        if (monster.getResistanceList().contains(idt.getDamageType())) {
                            score /= 2;
                        }

                        if (score > bestScore) {
                            bestScore = score;
                        }
                        if (score >= monsterHPvar) {
                            bestScore = monsterHPvar;
                            break;
                        }

                    }


                    for (Status status : item.getItem_statusList()) {
                        if (monster.getImmunityStatusList().contains(status)) {
                            efficiency /= 1.2;
                        }
                    }

                    if (itemBestScore > bestScore) {
                        bestScore = itemBestScore;
                    }
                }
            }

        }


        if (character.getSpell_charList() != null) {
            for (Spell spell : character.getSpell_charList()) {

                for (Spell_DamageType sdt : spell.getSpellDamageTypeList()) {
                    if (monster.getImmunityList().contains(sdt.getDamageType())) {
                        efficiency /= 1.4;
                    }
                    if (monster.getVulnerabilityList().contains(sdt.getDamageType())) {
                        efficiency *= 1.2;
                    }
                    if (monster.getResistanceList().contains(sdt.getDamageType())) {
                        efficiency /= 1.2;
                    }

                }
                for (Status status : spell.getSpell_statusList()) {
                    if (monster.getImmunityStatusList().contains(status)) {
                        efficiency /= 1.2;
                    }
                }
            }
        }


        return efficiency;
    }
}