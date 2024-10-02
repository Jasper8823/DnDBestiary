package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping("/fillDBMonster")
    public String createMonsterForm(Model model) {
        model.addAttribute("monsterDTO", new MonsterDTO());
        return "fillDBMonster";
    }

    @PostMapping("/fillDBMonster")
    public String saveMonster(@ModelAttribute MonsterDTO monsterDTO, Model model) {
        monsterService.saveMonster(monsterDTO);
        model.addAttribute("message", "Monster created successfully!");
        return "fillDBMonster"; // Return to the form
    }
    @PostMapping(value = "/hello")
    @ResponseBody
    public void hello(@RequestBody String name) {
        System.out.println("Hello " + name); // Print to console
    }
}
