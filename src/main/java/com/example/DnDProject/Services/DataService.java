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

    public Map<String, List<Map<String, Object>>> getFilteredSortedItems(Map<String, String> filters) {
        String name = filters.getOrDefault("name", "").trim().toLowerCase();
        List<String> rarityFilters = Arrays.stream(filters.getOrDefault("rarity", "").trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> typeFilters = Arrays.stream(filters.getOrDefault("type", "").trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();
        String needsAdjustment = filters.getOrDefault("needsAdjustment", "").trim().toLowerCase();

        boolean filterNeedsAdjustment = !needsAdjustment.isEmpty();
        boolean needsAdjustValue = Boolean.parseBoolean(needsAdjustment);

        List<Item> items = itemRepo.findAll(Sort.by(
                Sort.Order.asc("rarity.name"),
                Sort.Order.asc("name")
        ));

        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();

        for (Item item : items) {
            boolean matches = false;

            String itemName = item.getName() != null ? item.getName().toLowerCase() : "";
            String itemRarity = item.getRarity() != null && item.getRarity().getName() != null
                    ? item.getRarity().getName().toLowerCase() : "";
            String itemType = item.getItemType() != null && item.getItemType().getName() != null
                    ? item.getItemType().getName().toLowerCase() : "";
            boolean itemConfigurable = item.isConfigurable();

            if (!name.isEmpty() && itemName.contains(name)) {
                matches = true;
            }

            if (!rarityFilters.isEmpty() && rarityFilters.contains(itemRarity)) {
                matches = true;
            }

            if (!typeFilters.isEmpty() && typeFilters.contains(itemType)) {
                matches = true;
            }

            if (filterNeedsAdjustment && itemConfigurable == needsAdjustValue) {
                matches = true;
            }

            if (name.isEmpty() && rarityFilters.isEmpty() && typeFilters.isEmpty() && !filterNeedsAdjustment) {
                matches = true;
            }

            if (matches) {
                Map<String, Object> info = new HashMap<>();
                info.put("name", item.getName());
                info.put("type", item.getItemType() != null ? item.getItemType().getName() : null);

                String rarityKey = item.getRarity() != null ? item.getRarity().getName() : "Unknown";
                result.computeIfAbsent(rarityKey, k -> new ArrayList<>()).add(info);
            }
        }

        return result;
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
        spellInfo.put("DamageTypes", DataFetchUtil.extractNames(spell.getSpellDamageTypeList(), idt -> idt.getDamageType().getName()));


        return spellInfo;
    }


    public Map<String, List<Map<String, Object>>> getFilteredSortedSpells(Map<String, String> filters) {
        List<Spell> allSpells = spellRepo.findAll();
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        String nameFilter = filters.getOrDefault("name", "").trim().toLowerCase();
        List<String> levelFilters = Arrays.stream(filters.getOrDefault("level", "").trim().split(","))
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> classFilters = Arrays.stream(filters.getOrDefault("charClass", "").trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> typeFilters = Arrays.stream(filters.getOrDefault("type", "").trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();

        for (Spell spell : allSpells) {
            boolean matches = true;

            if (!nameFilter.isEmpty() && !spell.getName().toLowerCase().contains(nameFilter)) {
                matches = false;
            }

            if (!levelFilters.isEmpty() && !levelFilters.contains(String.valueOf(spell.getLevel()))) {
                matches = false;
            }

            if (!classFilters.isEmpty()) {
                boolean hasClass = spell.getSpell_classList().stream()
                        .anyMatch(c -> classFilters.contains(c.getName().toLowerCase()));
                if (!hasClass) {
                    matches = false;
                }
            }

            if (!typeFilters.isEmpty()) {
                String spellType = spell.getSpellType() != null ? spell.getSpellType().getName().toLowerCase() : "";
                if (!typeFilters.contains(spellType)) {
                    matches = false;
                }
            }

            if (matches) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", spell.getName());
                map.put("level", spell.getLevel());

                String groupKey;
                if (spell.getName() != null && !spell.getName().isEmpty()) {
                    groupKey = spell.getName().substring(0, 1).toUpperCase();
                } else {
                    groupKey = "#";
                }

                result.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(map);
            }
        }

        for (List<Map<String, Object>> spells : result.values()) {
            spells.sort(Comparator.comparingInt(m -> (int) m.get("level")));
        }

        return result;
    }



    public Map<String, List<Map<String, Object>>> getFilteredSortedMonsters(Map<String, String> filters) {
        List<Monster> allMonsters = repo.findAll();
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        String nameFilter = filters.getOrDefault("name", "").trim().toLowerCase();

        List<String> sizeFilters = Arrays.stream(filters.getOrDefault("size", "")
                        .trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();

        List<String> typeFilters = Arrays.stream(filters.getOrDefault("type", "")
                        .trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();

        List<String> worldViewFilters = Arrays.stream(filters.getOrDefault("worldView", "")
                        .trim().toLowerCase().split(","))
                .filter(s -> !s.isEmpty())
                .toList();

        List<String> dangerFilters = Arrays.stream(filters.getOrDefault("danger", "")
                        .trim().split(","))
                .filter(s -> !s.isEmpty())
                .toList();

        for (Monster monster : allMonsters) {
            boolean matches = false;

            if (!nameFilter.isEmpty() && monster.getName().toLowerCase().contains(nameFilter)) {
                matches = true;
            }

            if (!sizeFilters.isEmpty() && sizeFilters.contains(monster.getSize().getName().toLowerCase())) {
                matches = true;
            }

            if (!typeFilters.isEmpty() && typeFilters.contains(monster.getType().getName().toLowerCase())) {
                matches = true;
            }

            if (!worldViewFilters.isEmpty() && worldViewFilters.contains(monster.getWorldview().getName().toLowerCase())) {
                matches = true;
            }

            if (!dangerFilters.isEmpty()) {
                for (String dangerVal : dangerFilters) {
                    try {
                        int dangerLevel = Integer.parseInt(dangerVal);
                        if (monster.getDanger().getDegree() == dangerLevel) {
                            matches = true;
                            break;
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }

            if (nameFilter.isEmpty() && sizeFilters.isEmpty() && typeFilters.isEmpty()
                    && worldViewFilters.isEmpty() && dangerFilters.isEmpty()) {
                matches = true;
            }

            if (matches) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", monster.getId());
                map.put("name", monster.getName());
                map.put("danger", monster.getDanger().getDegree());

                String groupKey;
                if (monster.getName() != null && !monster.getName().isEmpty()) {
                    groupKey = monster.getName().substring(0, 1).toUpperCase();
                } else {
                    groupKey = "#";
                }

                result.computeIfAbsent(groupKey, k -> new ArrayList<>()).add(map);
            }
        }

        for (List<Map<String, Object>> monsters : result.values()) {
            monsters.sort(Comparator.comparingDouble(m -> convertDangerCode((int) m.get("danger"))));
        }

        return result;
    }


    private double convertDangerCode(int code) {
        return switch (code) {
            case 108 -> 1.0 / 8.0;
            case 104 -> 1.0 / 4.0;
            case 102 -> 1.0 / 2.0;
            default -> code;
        };
    }

}

