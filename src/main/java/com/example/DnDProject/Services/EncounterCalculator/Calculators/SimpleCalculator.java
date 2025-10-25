package com.example.DnDProject.Services.EncounterCalculator.Calculators;
import static com.example.DnDProject.Services.EncounterCalculator.EncounterXPData.*;

import com.example.DnDProject.DTOs.Calculator.CharacterDTO;
import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.DTOs.Calculator.MonsterDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SimpleCalculator {

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

        for (MonsterDTO monster : dto.getMonsters()) {
            String cr = monster.getCr();
            int count = monster.getCount() != null ? monster.getCount() : 1;
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
