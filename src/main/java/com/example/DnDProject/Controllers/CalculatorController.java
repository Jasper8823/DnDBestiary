package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.Calculator.GeneralCalcDTO;
import com.example.DnDProject.Services.CharacterService;
import com.example.DnDProject.Services.DataService;
import com.example.DnDProject.Services.EncounterCalculator.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {

    @Autowired
    private Orchestrator orchestrator;

    @Autowired
    private DataService dataService;

    @Autowired
    private CharacterService charService;

    @PostMapping("/calculate")
    @ResponseBody
    public Map<String, String> calculate(@RequestBody GeneralCalcDTO dto) {
        String difficulty = orchestrator.calculate(dto);
        return Collections.singletonMap("difficulty", difficulty);
    }

    @GetMapping("/getCombatCalculator")
    @ResponseBody
    public Map<String, Object> getCombatCalculator(
            @RequestParam(value = "userid", required = false) Integer userId) {

        Map<String, Object> result = new HashMap<>();
        result.put("monsters", dataService.getAllMonstersBasicInfo());

        if (userId != null) {
            result.put("characters", charService.getAllCharactersBasicInfo(userId));
        } else {
            result.put("characters", new HashMap<>());
        }

        return result;
    }
}
