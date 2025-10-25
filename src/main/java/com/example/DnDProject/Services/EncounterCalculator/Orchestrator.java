package com.example.DnDProject.Services.EncounterCalculator;

import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.Enums.CharacterType;
import com.example.DnDProject.Enums.MonsterType;
import com.example.DnDProject.Services.EncounterCalculator.Calculators.AdvancedCalculator;
import com.example.DnDProject.Services.EncounterCalculator.Calculators.ExpertCalculator;
import com.example.DnDProject.Services.EncounterCalculator.Calculators.SimpleCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Orchestrator {

    @Autowired
    private SimpleCalculator simpleCalc;

    @Autowired
    private AdvancedCalculator advancedCalc;

    @Autowired
    private ExpertCalculator expertCalc;

    public String calculate(GeneralCalcDTO dto) {
        CharacterType charType = detectCharacterType(dto);
        MonsterType monType = detectMonsterType(dto);

        if (charType == CharacterType.CREATED && monType == MonsterType.COMPLEX) {
//            return expertCalc.calculate(dto);
        } else if (charType == CharacterType.ADVANCED && monType == MonsterType.COMPLEX) {
            return advancedCalc.calculate(dto);
        } else {
            return simpleCalc.calculate(dto);
        }
        return null;
    }

    private CharacterType detectCharacterType(GeneralCalcDTO dto) {
        if (dto.getPlayers().stream().anyMatch(p -> p.getId() != null)) {
            return CharacterType.CREATED;
        }
        if (dto.getPlayers().stream().anyMatch(p -> p.getClassName() != null && p.getLevel() != null)) {
            return CharacterType.ADVANCED;
        }
        return CharacterType.REGULAR;
    }

    private MonsterType detectMonsterType(GeneralCalcDTO dto) {
        if (dto.getMonsters().stream().anyMatch(m -> m.getId() != null)) {
            return MonsterType.COMPLEX;
        }
        return MonsterType.REGULAR;
    }

}
