package com.example.DnDProject.Controllers;


import com.example.DnDProject.DTOs.CalcDTO;
import com.example.DnDProject.Services.DataService;
import com.example.DnDProject.Services.DatafillService;
import com.example.DnDProject.Services.EncounterCalculator.CombatCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {

    @Autowired
    private CombatCalculator combatCalculator;

    @PostMapping("/calculate")
    @ResponseBody
    public Map<String, String> calculate(@RequestBody CalcDTO dto) {
        String difficulty = combatCalculator.calculateSimpleDifficulty(dto);
        return Collections.singletonMap("difficulty", difficulty);
    }

}
