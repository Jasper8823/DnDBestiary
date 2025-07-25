package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Monster.Location.Location;
import com.example.DnDProject.Entities.Monster.Status.Status;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.Repositories.Spell.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@Validated
public class DataService {

    @Autowired
    private MonsterRepository repo;

    @Autowired
    private SpellRepository spellRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private DataFetchUtil dfu;


    public Map<String, Object> monsterInfo(int id) {
        Monster monster = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Monster not found with id: " + id));


        Map<String, Object> monsterInfo = new HashMap<>();

        monsterInfo.put("name", monster.getName());
        monsterInfo.put("armor_class", monster.getArmor_class());
        monsterInfo.put("walk_speed", monster.getSpeed());
        monsterInfo.put("swim_speed", monster.getSwim_speed());
        monsterInfo.put("fly_speed", monster.getFly_speed());

        monsterInfo.put("avgHP", monster.getAvg_HP());
        monsterInfo.put("calcHP",monster.getCalc_HP());

        monsterInfo.put("strength", monster.getStrength());
        monsterInfo.put("dexterity", monster.getDexterity());
        monsterInfo.put("charisma", monster.getCharisma());
        monsterInfo.put("intelligence", monster.getIntelligence());
        monsterInfo.put("wisdom", monster.getWisdom());
        monsterInfo.put("bodybuild", monster.getConstitution());

        monsterInfo.put("perception", monster.getPerception());
        monsterInfo.put("skill_bonus", monster.getSkill_bonus());

        monsterInfo.put("strength_bonus", monster.getStrength_bonus());
        monsterInfo.put("dexterity_bonus", monster.getDexterity_bonus());
        monsterInfo.put("charisma_bonus", monster.getCharisma_bonus());
        monsterInfo.put("intelligence_bonus", monster.getIntelligence_bonus());
        monsterInfo.put("wisdom_bonus", monster.getWisdom_bonus());
        monsterInfo.put("bodybuild_bonus", monster.getConstitution_bonus());

        monsterInfo.put("features", monster.getFeatures());
        monsterInfo.put("description", monster.getDescription());

        monsterInfo.put("danger", monster.getDanger());
        monsterInfo.put("size", monster.getSize().getName());
        monsterInfo.put("type", monster.getType().getName());
        monsterInfo.put("worldView", monster.getWorldview().getName());

        monsterInfo.put("immunityList", DataFetchUtil.extractNames(monster.getImmunityList(), DamageType::getName));
        monsterInfo.put("resistanceList", DataFetchUtil.extractNames(monster.getResistanceList(), DamageType::getName));
        monsterInfo.put("vulnerabilityList", DataFetchUtil.extractNames(monster.getVulnerabilityList(), DamageType::getName));
        monsterInfo.put("immunityStatusList", DataFetchUtil.extractNames(monster.getImmunityStatusList(), Status::getName));
        monsterInfo.put("habitats", DataFetchUtil.extractNames(monster.getHabitats(), Location::getName));
        monsterInfo.put("actions", dfu.getActions(monster.getMonsterActions()));


        return monsterInfo;
    }



    public Map<String, Object> itemInfo(String id) {
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));


        Map<String, Object> itemInfo = new HashMap<>();

        itemInfo.put("name", item.getName());
        itemInfo.put("description", item.getDescription());
        itemInfo.put("configurable", item.isConfigurable());
        itemInfo.put("item_type_name", item.getItemType().getName());
        itemInfo.put("rarity_name", item.getRarity().getName());
        if(item.getSubType() != null) {
            itemInfo.put("subtype", item.getSubType().getName());
        }

        itemInfo.put("StatusList", DataFetchUtil.extractNames(item.getItem_statusList(), Status::getName));
        itemInfo.put("DamageTypes", DataFetchUtil.extractNames(item.getItemDamageTypeList(), idt -> idt.getDamageType().getName()));

        return itemInfo;
    }



    public List<Map<String, Object>> getFilteredSortedItems(Map<String, String> filters) {
        String name = filters.getOrDefault("name", "").trim().toLowerCase();
        String rarity = filters.getOrDefault("rarity", "").trim().toLowerCase();
        String type = filters.getOrDefault("type", "").trim().toLowerCase();
        String needsAdjustment = filters.getOrDefault("needsAdjustment", "").trim().toLowerCase();

        boolean filterNeedsAdjustment = !needsAdjustment.isEmpty();
        boolean needsAdjustValue = Boolean.parseBoolean(needsAdjustment);

        List<Item> items = itemRepo.findAll(Sort.by(
                Sort.Order.asc("rarity.name"),
                Sort.Order.asc("name")
        ));

        List<Map<String, Object>> filteredItems = new ArrayList<>();

        for (Item item : items) {
            String itemName = item.getName() != null ? item.getName().toLowerCase() : "";
            String itemRarity = item.getRarity() != null && item.getRarity().getName() != null
                    ? item.getRarity().getName().toLowerCase() : "";
            String itemType = item.getItemType() != null && item.getItemType().getName() != null
                    ? item.getItemType().getName().toLowerCase() : "";
            boolean itemConfigurable = item.isConfigurable();

            if (!name.isEmpty() && !itemName.contains(name)) continue;
            if (!rarity.isEmpty() && !itemRarity.equals(rarity)) continue;
            if (!type.isEmpty() && !itemType.equals(type)) continue;
            if (filterNeedsAdjustment && itemConfigurable != needsAdjustValue) continue;

            Map<String, Object> info = new HashMap<>();
            info.put("name", item.getName());
            info.put("type", item.getItemType() != null ? item.getItemType().getName() : null);
            filteredItems.add(info);
        }

        return filteredItems;
    }



    public Map<String, Object> spellInfo(String id) {
        Spell spell = spellRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Spell not found with id: " + id));


        Map<String, Object> spellInfo = new HashMap<>();

        spellInfo.put("name", spell.getName());
        spellInfo.put("description", spell.getDescription());
        spellInfo.put("concentration", spell.isConcentration());
        spellInfo.put("type", spell.getSpellType().getName());
        spellInfo.put("duration", spell.getDuration());
        spellInfo.put("level", spell.getLevel());
        spellInfo.put("target", spell.getTarget());
        spellInfo.put("distance", spell.getDistance());
        spellInfo.put("concentDura", spell.getConcentDura());
        spellInfo.put("spell_subtype", spell.getSpellType().getName());
        spellInfo.put("prepareMoves", spell.getPrepareMoves());
        spellInfo.put("status_names", DataFetchUtil.extractNames(spell.getSpell_statusList(), Status::getName));
        spellInfo.put("class_names", DataFetchUtil.extractNames(spell.getSpell_classList(), CharacterClass::getName));
        spellInfo.put("DamageTypes", DataFetchUtil.extractNames(spell.getSpellDamageTypeList(), sdt -> sdt.getDamageType().getName()));


        return spellInfo;
    }


    public List<Map<String, Object>> getFilteredSortedSpells(Map<String, String> filters) {
        List<Spell> allSpells = spellRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        String nameFilter = filters.getOrDefault("name", "").trim().toLowerCase();
        String levelFilter = filters.getOrDefault("level", "").trim();
        String classFilter = filters.getOrDefault("charClass", "").trim().toLowerCase();
        String typeFilter = filters.getOrDefault("type", "").trim().toLowerCase();


        for (Spell spell : allSpells) {
            boolean matches = nameFilter.isEmpty() || spell.getName().toLowerCase().contains(nameFilter);

            if (!levelFilter.isEmpty()) {
                try {
                    int level = Integer.parseInt(levelFilter);
                    if (spell.getLevel() != level) {
                        matches = false;
                    }
                } catch (NumberFormatException e) {
                    matches = false;
                }
            }

            if (!classFilter.isEmpty()) {
                boolean hasClass = spell.getSpell_classList().stream()
                        .anyMatch(c -> c.getName().equalsIgnoreCase(classFilter));
                if (!hasClass) {
                    matches = false;
                }
            }

            if (!typeFilter.isEmpty()) {
                String spellType = spell.getSpellType() != null ? spell.getSpellType().getName().toLowerCase() : "";
                if (!spellType.equals(typeFilter)) {
                    matches = false;
                }
            }

            if (matches) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", spell.getName());
                map.put("level", spell.getLevel());

                result.add(map);
            }
        }

        return result;
    }


    public List<Map<String, Object>> getFilteredSortedMonsters(Map<String, String> filters) {
        List<Monster> allMonsters = repo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        String nameFilter = filters.getOrDefault("name", "").trim().toLowerCase();
        String sizeFilter = filters.getOrDefault("size", "").trim().toLowerCase();
        String typeFilter = filters.getOrDefault("type", "").trim().toLowerCase();
        String worldViewFilter = filters.getOrDefault("worldView", "").trim().toLowerCase();
        String dangerFilter = filters.getOrDefault("danger", "").trim();


        for (Monster monster : allMonsters) {
            boolean matches = nameFilter.isEmpty() || monster.getName().toLowerCase().contains(nameFilter);

            if (!sizeFilter.isEmpty() && !monster.getSize().getName().toLowerCase().equals(sizeFilter)) {
                matches = false;
            }

            if (!typeFilter.isEmpty() && !monster.getType().getName().toLowerCase().equals(typeFilter)) {
                matches = false;
            }

            if (!worldViewFilter.isEmpty() && !monster.getWorldview().getName().toLowerCase().equals(worldViewFilter)) {
                matches = false;
            }

            if (!dangerFilter.isEmpty()) {
                try {
                    int dangerLevel = Integer.parseInt(dangerFilter);
                    if (monster.getDanger().getDegree() != dangerLevel) {
                        matches = false;
                    }
                } catch (NumberFormatException e) {
                    matches = false;
                }
            }

            if (matches) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", monster.getId());
                map.put("name", monster.getName());
                map.put("danger", monster.getDanger().getDegree());
                result.add(map);
            }
        }
        return result;
    }

}

