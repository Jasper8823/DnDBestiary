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

import java.util.Map;

@Controller
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping("/fillDBMonster")
    public String createMonsterForm() {
        return "fillDBMonster";
    }


    @PostMapping("/monsterPush")
    @ResponseBody
    public ResponseEntity<String> monsterPush(@RequestBody MonsterDTO monster) throws Throwable {
        monsterService.saveMonster(monster);
        return ResponseEntity.ok("Monster received successfully!");
    }

}
