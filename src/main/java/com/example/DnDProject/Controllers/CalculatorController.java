package com.example.DnDProject.Controllers;


import com.example.DnDProject.Services.DatafillService;
import com.example.DnDProject.Services.EncounterCalculator.CombatCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {

    @Autowired
    private CombatCalculator combatCalculator;




}
