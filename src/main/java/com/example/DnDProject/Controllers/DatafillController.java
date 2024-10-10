package com.example.DnDProject.Controllers;

import com.example.DnDProject.Configurations.WebPageOpener;
import com.example.DnDProject.DTOs.ClassAbilityDTO;
import com.example.DnDProject.DTOs.ItemDTO;
import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.DTOs.SpellDTO;
import com.example.DnDProject.Services.DatafillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DatafillController {

    @Autowired
    private DatafillService datafillService;

    WebPageOpener webPageOpener = new WebPageOpener();

    @GetMapping("/fillDBMonster")
    public String createMonsterForm() {
        return "fillDBMonster";
    }


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



    @GetMapping("/Monster")
    public String Monster() {
        webPageOpener.openWebPage("http://localhost:8080/fillDBMonster");
        return "fillDBMonster";
    }


    @GetMapping("/Spell")
    public String Spell() {
        webPageOpener.openWebPage("http://localhost:8080/fillDBSpell");
        return "fillDBMonster";
    }


    @GetMapping("/Item")
    public String Item() {
        webPageOpener.openWebPage("http://localhost:8080/fillDBItem");
        return "fillDBMonster";
    }


    @GetMapping("/Ability")
    public String Ability() {
        webPageOpener.openWebPage("http://localhost:8080/fillDBAbility");
        return "fillDBMonster";
    }


    @PostMapping("/monsterPush")
    @ResponseBody
    public ResponseEntity<String> monsterPush(@RequestBody MonsterDTO monster){
        datafillService.saveMonster(monster);
        return ResponseEntity.ok("Monster received successfully!");
    }

    @PostMapping("/spellPush")
    @ResponseBody
    public ResponseEntity<String> spellPush(@RequestBody SpellDTO spellDTO){
        datafillService.saveSpell(spellDTO);
        return ResponseEntity.ok("Spell received successfully!");
    }


    @PostMapping("/itemPush")
    @ResponseBody
    public ResponseEntity<String> itemPush(@RequestBody ItemDTO itemDTO){
        datafillService.saveItem(itemDTO);
        return ResponseEntity.ok("Item received successfully!");
    }

    @PostMapping("/classAbilityPush")
    @ResponseBody
    public ResponseEntity<String> classAbilityPush(@RequestBody ClassAbilityDTO abilityDTO){
        datafillService.saveClassAbility(abilityDTO);
        return ResponseEntity.ok("ClassAbility received successfully!");
    }
}
