package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.SpellSlots;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.Character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@Validated
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepo;

    public Map<String, List<Map<String, Object>>> charactersInfo() {
        List<Character> characters = characterRepo.findAll();

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        for (Character character : characters) {
            Map<String, Object> charInfo = new HashMap<>();
            charInfo.put("name", character.getName());
            charInfo.put("class", character.getCharClass().getName());
            charInfo.put("level", character.getLevel());

            String groupKey;
            if (character.getName() != null && !character.getName().isEmpty()) {
                groupKey = character.getName().substring(0, 1).toUpperCase();
            } else {
                groupKey = "#";
            }

            result.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(charInfo);
        }

        for (List<Map<String, Object>> group : result.values()) {
            group.sort(Comparator.comparingInt(c -> (int) c.get("level")));
        }

        return result;
    }

    public Map<String, Object> characterInfo(int id) {
        Character character = characterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

        Map<String, Object> charInfo = new HashMap<>();
        charInfo.put("name", character.getName());
        charInfo.put("level", character.getLevel());

        if (character.getCharClass() != null) {
            charInfo.put("class", character.getCharClass().getName());
        }
        if (character.getRace() != null) {
            charInfo.put("race", character.getRace().getName());
        }
        charInfo.put("backstory", character.getBackstory().getName());

        Map<String, Object> stats = new HashMap<>();
        stats.put("strength", character.getStrength());
        stats.put("dexterity", character.getDexterity());
        stats.put("constitution", character.getConstitution());
        stats.put("intelligence", character.getIntelligence());
        stats.put("wisdom", character.getWisdom());
        stats.put("charisma", character.getCharisma());
        charInfo.put("stats", stats);


        if (character.getSpell_charList() != null) {
            List<Map<String, Object>> spells = new ArrayList<>();
            for (Spell spell : character.getSpell_charList()) {
                Map<String, Object> spellInfo = new HashMap<>();
                spellInfo.put("name", spell.getName());
                spellInfo.put("level", spell.getLevel());
                spells.add(spellInfo);
            }
            charInfo.put("spells", spells);
        }

        if (character.getItem_charList() != null) {
            List<Map<String, Object>> items = new ArrayList<>();
            for (Item item : character.getItem_charList()) {
                Map<String, Object> itemInfo = new HashMap<>();
                itemInfo.put("name", item.getName());
                itemInfo.put("type", item.getItemType().getName());
                items.add(itemInfo);
            }
            charInfo.put("items", items);
        }

        SpellSlots slots = character.getCharClass().getSpellSlotsList().stream()
                .filter(slot -> slot.getLevel() == character.getLevel()
                        && slot.getCharClass().getName().equals(character.getCharClass().getName()))
                .findFirst()
                .orElse(null);

        if (slots != null) {
            Map<String, Object> slotInfo = new HashMap<>();
            slotInfo.put("level", slots.getLevel());
            slotInfo.put("lvl1", slots.getLvl1());
            slotInfo.put("lvl2", slots.getLvl2());
            slotInfo.put("lvl3", slots.getLvl3());
            slotInfo.put("lvl4", slots.getLvl4());
            slotInfo.put("lvl5", slots.getLvl5());
            slotInfo.put("lvl6", slots.getLvl6());
            slotInfo.put("lvl7", slots.getLvl7());
            slotInfo.put("lvl8", slots.getLvl8());
            slotInfo.put("lvl9", slots.getLvl9());
            slotInfo.put("plot_num", slots.getPlot_num());
            slotInfo.put("spell_num", slots.getSpell_num());

            charInfo.put("slots", slotInfo);
        }

        return charInfo;
    }



}
