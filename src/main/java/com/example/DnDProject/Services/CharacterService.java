package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.Entities.Class.ClassAbility;
import com.example.DnDProject.Entities.Class.SpellSlots;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Item.SubType;
import com.example.DnDProject.Entities.Race.RaceAbility;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.Character.CharacterRepository;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@Validated
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepo;

    @Autowired
    private ItemRepository itemRepo;

    public Map<String, List<Map<String, Object>>> charactersInfo(int userId) {
        List<Character> characters = characterRepo.findAll();

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        for (Character character : characters) {
            if (character.getUser() == null || character.getUser().getId() != userId) {
                continue;
            }

            Map<String, Object> charInfo = new HashMap<>();
            charInfo.put("id", character.getId());
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

        if (character.getCharClass() != null){
            charInfo.put("class", character.getCharClass().getName());
            if(character.getCharClass().getParentClass() != null){
                charInfo.put("parentClass", character.getCharClass().getParentClass().getName());
            } else {
                if (character.getCharClass().getAbilityList() != null) {
                    List<Map<String, Object>> abilities = new ArrayList<>();
                    for (ClassAbility ability : character.getCharClass().getAbilityList()) {
                        Map<String, Object> abilitiesInfo = new HashMap<>();
                        abilitiesInfo.put("name", ability.getName());
                        abilitiesInfo.put("level", ability.getLevel());
                        abilitiesInfo.put("description", ability.getDescription());
                        abilities.add(abilitiesInfo);
                    }
                    charInfo.put("class_abilities", abilities);
                }

                if (character.getCharClass().getSubtype_classList() != null) {
                    List<Map<String, Object>> proficiencies = new ArrayList<>();
                    for (SubType prof : character.getCharClass().getSubtype_classList()) {
                        Map<String, Object> profInfo = new HashMap<>();
                        profInfo.put("name", prof.getName());
                        proficiencies.add(profInfo);
                    }
                    charInfo.put("proficiencies", proficiencies);
                }
            }
        }
        if (character.getRace() != null) {
            charInfo.put("race", character.getRace().getName());
            Map<String, Object> raceInfo = new HashMap<>();
            if (character.getRace().getAbilities() != null) {
                List<Map<String,Object>> abilities = new ArrayList<>();
               for(RaceAbility ability : character.getRace().getAbilities()) {
                   raceInfo.put("name", ability.getName());
                   raceInfo.put("description",ability.getDescription());
                   abilities.add(raceInfo);
               }
               charInfo.put("race_abilities", abilities);
            }

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
                spellInfo.put("description", spell.getDescription());
                spells.add(spellInfo);
            }
            charInfo.put("spells", spells);
        }

        if (character.getItem_charList() != null) {
            List<Map<String, Object>> ownedItems = new ArrayList<>();
            for (Item item : character.getItem_charList()) {
                Map<String, Object> itemInfo = new HashMap<>();
                itemInfo.put("name", item.getName());
                itemInfo.put("rarity", item.getRarity().getName());
                itemInfo.put("description", item.getDescription());
                ownedItems.add(itemInfo);
            }
            charInfo.put("ownedItems", ownedItems);

            List<Item> allItemsFromDb = itemRepo.findAll();
            List<Map<String, Object>> allItems = new ArrayList<>();
            for (Item item : allItemsFromDb) {
                Map<String, Object> itemInfo = new HashMap<>();
                itemInfo.put("name", item.getName());
                itemInfo.put("rarity", item.getRarity().getName());
                itemInfo.put("description", item.getDescription());
                allItems.add(itemInfo);
            }
            charInfo.put("allItems", allItems);
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



    @Transactional
    public void addItemToCharacter(Map<String, Object> requestData) {
        String itemId = (String) requestData.get("itemId");
        int characterId = Integer.parseInt((String) requestData.get("id"));

        Character character = characterRepo.findById(characterId).
                orElseThrow(() -> new RuntimeException("Character not found with id: " + characterId));
        Item item = itemRepo.findById(itemId).
                orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
        if(!character.getItem_charList().contains(item)) {
            character.getItem_charList().add(item);
            characterRepo.save(character);
        }
    }

    @Transactional
    public void removeItemFromCharacter(Map<String, Object> requestData) {
        String itemId = (String) requestData.get("itemId");
        int characterId = Integer.parseInt((String) requestData.get("id"));

        Character character = characterRepo.findById(characterId).
                orElseThrow(() -> new RuntimeException("Character not found with id: " + characterId));
        Item item = itemRepo.findById(itemId).
                orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
        character.getItem_charList().remove(item);
        characterRepo.save(character);
    }

    public List<Map<String, Object>> getAllCharactersBasicInfo(Integer userId) {
        List<Character> characters = characterRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Character c : characters) {
            if (c.getUser() == null || c.getUser().getId() != userId) continue;

            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("name", c.getName());
            map.put("level", c.getLevel());
            result.add(map);
        }

        return result;
    }


}
