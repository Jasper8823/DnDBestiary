package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.ClassAbilityDTO;
import com.example.DnDProject.Services.DatafillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class ClassAbilityController {

    @Autowired
    private DatafillService datafillService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/fillDBAbility")
    public String createAbilityForm() {
        return "fillDBAbility";
    }

    @PostMapping("/classAbilityPush")
    @ResponseBody
    public ResponseEntity<String> classAbilityPush(@RequestBody ClassAbilityDTO abilityDTO){
        datafillService.saveClassAbility(abilityDTO);
        return ResponseEntity.ok("ClassAbility received successfully!");
    }

}
