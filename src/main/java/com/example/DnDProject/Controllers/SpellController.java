package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.SpellDTO;
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
public class SpellController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DatafillService datafillService;

    @GetMapping("/fillDBSpell")
    public String createSpellForm() {
        return "fillDBSpell";
    }

    @PostMapping("/spellPush")
    @ResponseBody
    public ResponseEntity<String> spellPush(@RequestBody SpellDTO spellDTO){
        datafillService.saveSpell(spellDTO);
        return ResponseEntity.ok("Spell received successfully!");
    }

    @GetMapping("/getSpell")
    @ResponseBody
    public Map<String, Object> getSpell(@RequestParam("id") String id) {
        return dataService.spellInfo(id);
    }

    @GetMapping("/getSpells")
    @ResponseBody
    public List<Map<String, Object>> getSpells(@RequestParam  Map<String, String> filters) {
        return dataService.getFilteredSortedSpells(filters);
    }


}

