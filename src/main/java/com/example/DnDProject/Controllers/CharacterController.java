package com.example.DnDProject.Controllers;

import com.example.DnDProject.Cache.CharacterCache;
import com.example.DnDProject.DTOs.CharacterDtos.CharSpellDTO;
import com.example.DnDProject.DTOs.CharacterDtos.CharacterDTO;
import com.example.DnDProject.DTOs.CharacterDtos.CharacterWSpellsDTO;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Services.CharacterService;
import com.example.DnDProject.Services.DatafillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class CharacterController{

    @Autowired
    private DatafillService dataFillService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterCache cache;


    @GetMapping("/getCharacters")
    @ResponseBody
    public List<Map<String, Object>> getCharacters(){
        return characterService.charactersInfo();
    }

    @PostMapping("/create-character")
    @ResponseBody
    public String createCharacter(@RequestBody CharacterDTO dto) {
        return cache.createCharacter(dto);
    }

    @GetMapping("/create-character")
    @ResponseBody
    public CharSpellDTO getCharacterProgress(@RequestParam String uuid) {
        CharSpellDTO dto = cache.getCharSpells(uuid);
        System.out.println(dto);
        return dto;
    }

    @PostMapping("/create-character-spells")
    @ResponseBody
    public void createCharacterSpells(@RequestBody CharacterWSpellsDTO dto) {
        Character character = cache.getCharacter(dto.getUuid());
        dataFillService.saveCharacter(character, dto);
    }
}