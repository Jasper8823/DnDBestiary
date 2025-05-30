package com.example.DnDProject.Controllers;

import com.example.DnDProject.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class ViewController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/fillDBMonster")
    public String createMonsterForm() { return "fillDBMonster";}
    @GetMapping("/fillDBSpell")
    public String createSpellForm() {
        return "fillDBSpell";
    }
    @GetMapping("/fillDBItem")
    public String createItemForm() {
        return "fillDBItem";
    }
    @GetMapping("/fillDBAbility")
    public String createAbilityForm() {
        return "fillDBAbility";
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

    @GetMapping("/getItem")
    @ResponseBody
    public Map<String, Object> getItem(@RequestParam("id") String id) {
        return dataService.itemInfo(id);
    }

    @GetMapping("/getItems")
    @ResponseBody
    public List<Map<String, Object>> getItems() {
        return dataService.itemsInfo();
    }

    @GetMapping("/getSpell")
    @ResponseBody
    public Map<String, Object> getSpell(@RequestParam("id") String id) {
        System.out.println(id);
        System.out.println(dataService.spellInfo(id).toString());
        return dataService.spellInfo(id);
    }

    @GetMapping("/getSpells")
    @ResponseBody
    public List<Map<String, Object>> getSpells() {
        return dataService.spellsInfo();
    }

}
