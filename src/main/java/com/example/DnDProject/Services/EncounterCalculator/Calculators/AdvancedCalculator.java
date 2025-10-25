package com.example.DnDProject.Services.EncounterCalculator.Calculators;

import com.example.DnDProject.DTOs.Calculator.CharacterDTO;
import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.DTOs.Calculator.MonsterDTO;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Monster.Topography.Topography;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.*;


@Service
@Validated
public class AdvancedCalculator {
    @Autowired
    private MonsterRepository monsterRepo;

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
                    modifier *= 0.9;
                }
                if (monster.getTopographyWeakList().contains(top)) {
                    modifier *= 1.1;
                }
            }


            if (monster != null) {
                for (CharacterDTO player : dto.getPlayers()) {
                    if (player.getClassName() == null) continue;

                    CharacterClass charClass = new CharacterClass();
                    charClass.setName(player.getClassName());

                    if (monster.getClassAdvList().contains(charClass)) {
                        modifier *= 0.7;
                    }
                    if (monster.getClassWeakList().contains(charClass)) {
                        modifier *= 1.3;
                    }
                }
            }

            totalMonsterXP += baseXP * count * modifier;
            totalMonsters += count;
        }

        double multiplier = getEncounterMultiplier(
                totalMonsters,
                dto.getPlayers().size()
        );

        double finalXP = totalMonsterXP * multiplier;

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
