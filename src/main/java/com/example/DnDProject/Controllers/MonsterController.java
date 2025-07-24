package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Services.DataService;
import com.example.DnDProject.Services.DatafillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class MonsterController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DatafillService datafillService;

    @GetMapping("/fillDBMonster")
    public String createMonsterForm() { return "fillDBMonster";}

    @PostMapping("/monsterPush")
    @ResponseBody
    public ResponseEntity<String> monsterPush(@RequestBody MonsterDTO monster){
        datafillService.saveMonster(monster);
        return ResponseEntity.ok("Monster received successfully!");
    }



    @GetMapping("/getMonster")
    @ResponseBody
    public Map<String, Object> getMonster(@RequestParam("id") int id) {
        return dataService.monsterInfo(id);
    }

    @GetMapping("/getMonsters")
    @ResponseBody
    public List<Map<String, Object>> getMonsters() {
        return dataService.monstersInfo();
    }
    @PostMapping("/getMonsters/sort")
    @ResponseBody
    public List<Map<String, Object>> getSortedFilteredMonsters(@RequestBody Map<String, String> filters) {
        System.out.println(dataService.getFilteredSortedMonsters(filters));
        return dataService.getFilteredSortedMonsters(filters);
    }

}