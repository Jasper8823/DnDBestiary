package com.example.DnDProject.Controllers;

import com.example.DnDProject.Cache.CharacterCache;
import com.example.DnDProject.DTOs.CharacterDtos.CharSpellDTO;
import com.example.DnDProject.DTOs.CharacterDtos.CharacterDTO;
import com.example.DnDProject.DTOs.CharacterDtos.CharacterWSpellsDTO;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Services.CharacterService;
import com.example.DnDProject.Services.DatafillService;
import com.example.DnDProject.Services.SessionManager;
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

    @Autowired
    private SessionManager sessionManager;

    @GetMapping("/getCharacters")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> getCharacters(@RequestParam("userid") String sessionId) {
        int userId = sessionManager.getUserId(sessionId);
        return characterService.charactersInfo(userId);
    }


    @GetMapping("/getCharacter")
    @ResponseBody
    public Map<String, Object> getCharacter(@RequestParam("id") int id) {
        return characterService.characterInfo(id);
    }

    @PostMapping("/create-character")
    @ResponseBody
    public String createCharacter(@RequestBody CharacterDTO dto) {
        return cache.createCharacter(dto);
    }

    @GetMapping("/create-character")
    @ResponseBody
    public CharSpellDTO getCharacterProgress(@RequestParam String uuid) {
        CharSpellDTO dto = cache.getCharSpells(uuid, dataFillService);
        System.out.println(dto);
        return dto;
    }

    @PostMapping("/create-character-spells")
    @ResponseBody
    public void createCharacterSpells(@RequestBody CharacterWSpellsDTO dto) {
        Character character = cache.getCharacter(dto.getUuid());
        dataFillService.saveCharacter(character, dto);
    }

    @PostMapping("/character-item-add")
    @ResponseBody
    public void addItemToCharacter(@RequestBody Map<String, Object> requestData) {
        characterService.addItemToCharacter(requestData);
    }


    @PostMapping("/character-item-remove")
    @ResponseBody
    public void removeItemFromCharacter(@RequestBody Map<String, Object> requestData) {
        characterService.removeItemFromCharacter(requestData);
    }

}