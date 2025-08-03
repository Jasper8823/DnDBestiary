package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Repositories.Character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepo;

    public List<Map<String, Object>> charactersInfo() {
        List<Character> characters = characterRepo.findAll();

        List<Map<String, Object>> result = new ArrayList<>();
        for (Character character : characters) {
            Map<String, Object> charInfo = new HashMap<>();
            charInfo.put("name", character.getName());
            charInfo.put("class", character.getCharClass().getName());
            charInfo.put("level", character.getLevel());
            result.add(charInfo);
        }

        return result;
    }


}
