package com.example.DnDProject.Services.EncounterCalculator.Calculators;
import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.*;

import com.example.DnDProject.DTOs.Calculator.CharacterDTO;
import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.DTOs.Calculator.MonsterDTO;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SimpleCalculator {

    @Autowired
    private MonsterRepository monsterRepo;
    public String calculate(GeneralCalcDTO dto) {
        if (dto == null || dto.getPlayers() == null || dto.getPlayers().isEmpty() ||
        dto.getMonsters() == null || dto.getMonsters().isEmpty()) {
            return "No players or monsters";
        }

        int easyThreshold = 0;
        int mediumThreshold = 0;
        int hardThreshold = 0;
        int deadlyThreshold = 0;
        for (CharacterDTO player : dto.getPlayers()) {
            int level = player.getLevel();
            int count = player.getCount() != null ? player.getCount() : 1;
            int[] thresholds = LEVEL_THRESHOLDS.get(level);
            easyThreshold += thresholds[0] * count;
            mediumThreshold += thresholds[1] * count;
            hardThreshold += thresholds[2] * count;
            deadlyThreshold += thresholds[3] * count;
        }

        int totalMonsterXP = 0;
        int totalMonsters = 0;

        for (MonsterDTO monsterDTO : dto.getMonsters()) {

            int count = monsterDTO.getCount() != null ? monsterDTO.getCount() : 1;
            String cr;

            if (monsterDTO.getId() != null) {
                Monster dbMonster = monsterRepo.findById(monsterDTO.getId()).orElse(null);
                if (dbMonster == null) continue;

                cr = String.valueOf(dbMonster.getDanger().getDegree());
            } else {
                cr = monsterDTO.getCr();
            }

            int xp = CR_XP.getOrDefault(cr, 0);
            totalMonsterXP += xp * count;
            totalMonsters += count;
        }

        double multiplier = getEncounterMultiplier(
                totalMonsters,
                dto.getPlayers().stream().mapToInt(p -> p.getCount() != null ? p.getCount() : 1).sum()
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