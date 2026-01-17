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
import com.example.DnDProject.Repositories.Character.CharacterRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.LEVEL_THRESHOLDS;
import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.getEncounterMultiplier;

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
            int count = 1;
            int[] thresholds = LEVEL_THRESHOLDS.get(level);
            easyThreshold += thresholds[0] * count;
            mediumThreshold += thresholds[1] * count;
            hardThreshold += thresholds[2] * count;
            deadlyThreshold += thresholds[3] * count;
        }

        double totalMonsterXP = 0;
        int totalMonsters = 0;

        for (MonsterDTO monsterDTO : dto.getMonsters()) {
            Monster monster = null;

            if (monsterDTO.getId() != null) {
                monster = monsterRepo.findById(monsterDTO.getId()).orElse(null);
                if (monster == null) continue;
            }

            int count = 1;
            int baseXP = 0;

            if(monster!= null) {
                baseXP = monster.getDanger().getExpGain();
            }

            double modifier = 1.0;

            if (monster != null && dto.getTopography() != null) {
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
            if (monster != null) {

                for (CharacterDTO player : dto.getPlayers()) {
                    Character character = null;

                    if (player.getId() != null) {
                        character = characterRepo.findById(player.getId()).orElse(null);
                        if (character == null) continue;
                    }


                    String[] dexterityWeapons = {
                            "dagger", "rapier", "short sword", "scimitar",
                            "whip", "dart", "light crossbow", "short bow",
                            "sling", "hand crossbow",
                            "heavy crossbow", "long bow",
                            "blowgun", "net"
                    };

                    double bestEffectiveDamage = 0;


                    for (Item item : character.getItem_charList()) {
                        if (!item.getItemType().getName().equals("weapon")) continue;

                        String itemName = item.getSubType().getName().toLowerCase();
                        boolean isDexWeapon = Arrays.asList(dexterityWeapons).contains(itemName);

                        boolean isProficient = character.getCharClass().getSubtype_classList().stream()
                                .anyMatch(cp -> cp.getName().equals(item.getSubType().getName())) ||
                                character.getRace().getRaceProfList().stream()
                                        .anyMatch(rp -> rp.getName().equals(item.getSubType().getName()));

                        int abilityModifier = (isDexWeapon && character.getDexterity() >= character.getStrength()) ?
                                (character.getDexterity() - 10) / 2 :
                                (character.getStrength() - 10) / 2;

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

                            // Internal per-damage-type fields
                            System.out.println("---- ITEM DAMAGE TYPE ----");
                            System.out.println("Item Name: " + item.getName());
                            System.out.println("Item Type: " + item.getItemType().getName());
                            System.out.println("SubType: " + item.getSubType().getName());
                            System.out.println("IsDexWeapon: " + isDexWeapon);
                            System.out.println("Proficient: " + isProficient);
                            System.out.println("AbilityModifier: " + abilityModifier);
                            System.out.println("AttackModifier: " + attackModifier);
                            System.out.println("NeededRoll: " + neededRoll);
                            System.out.println("HitChance: " + hitChance);
                            System.out.println("DamageType: " + idt.getDamageType().getName());
                            System.out.println("Raw AvgDamage: " + DataFetchUtil.calculateAvgHP(Integer.parseInt(parts[0]),
                                    Integer.parseInt(parts[1]),
                                    Integer.parseInt(parts[2])));
                            System.out.println("Adjusted AvgDamage: " + avgDamage);
                            System.out.println("EffectiveDamage: " + effectiveDamage);
                        }
                    }

                    double efficiencyModifier = Math.min(Math.max(bestEffectiveDamage * 2, 0), 2);

                    totalItemEfficiency += efficiencyModifier;
                    characterCount++;

                    System.out.println("==== CHARACTER / MONSTER SUMMARY ====");
                    System.out.println("Character Name: " + character.getName());
                    System.out.println("Character Class: " + character.getCharClass().getName());
                    System.out.println("Character Race: " + character.getRace().getName());
                    System.out.println("Strength: " + character.getStrength() + ", Dexterity: " + character.getDexterity());
                    System.out.println("ProficiencyBonus: " + character.getProficiencyBonus());
                    System.out.println("Monster Name: " + monster.getName());
                    System.out.println("Monster Armor Class: " + monster.getArmor_class());
                    System.out.println("Monster Avg HP: " + monster.getAvg_HP());
                    System.out.println("Monster HP var: " + monster.getAvg_HP());
                    System.out.println("Monster Immunities: " + monster.getImmunityList());
                    System.out.println("Monster Vulnerabilities: " + monster.getVulnerabilityList());
                    System.out.println("Monster Resistances: " + monster.getResistanceList());
                    System.out.println("Best Effective Damage: " + bestEffectiveDamage);
                    System.out.println("Efficiency Modifier: " + efficiencyModifier);
                }
            }
            double avgCenteredEfficiency = totalItemEfficiency / characterCount;


            totalMonsterXP += baseXP * count * modifier/avgCenteredEfficiency;
            totalMonsters += count;
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
}