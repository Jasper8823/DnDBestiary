package com.example.DnDProject.Controllers;

import com.example.DnDProject.Services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class CharacterController{

    @Autowired
    private CharacterService characterService;

    @GetMapping("/getCharacters")
    @ResponseBody
    public List<Map<String, Object>> getItems(){
        return characterService.charactersInfo();
    }
}