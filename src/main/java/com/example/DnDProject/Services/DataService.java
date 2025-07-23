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

    public List<Map<String, Object>> monstersInfo() {
        List<Monster> monsters = repo.findAll();

        monsters.sort(Comparator.comparingInt(m -> m.getDanger().getDegree()));

        List<Map<String, Object>> result = new ArrayList<>();
        for (Monster monster : monsters) {
            Map<String, Object> info = new HashMap<>();
            info.put("id", monster.getId());
            info.put("name", monster.getName());
            info.put("danger", monster.getDanger().getDegree());
            result.add(info);
        }

        return result;
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

    public List<Map<String, Object>> itemsInfo() {
        List<Item> items = itemRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));

        List<Map<String, Object>> result = new ArrayList<>();
        for (Item item : items) {
            Map<String, Object> info = new HashMap<>();
            info.put("name", item.getName());
            info.put("type", item.getItemType().getName());
            info.put("rarity", item.getRarity().getName());
            result.add(info);
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
        spellInfo.put("DamageTypes", DataFetchUtil.extractNames(spell.getSpellDamageTypeList(), sdt -> sdt.getDamageType().getName()));


        return spellInfo;
    }

    public List<Map<String, Object>> spellsInfo() {
        List<Spell> spells = spellRepo.findAll();

        spells.sort(Comparator.comparing(Spell::getLevel));

        List<Map<String, Object>> result = new ArrayList<>();
        for (Spell spell : spells) {
            Map<String, Object> info = new HashMap<>();
            info.put("name", spell.getName());
            info.put("type", spell.getSpellType().getName());
            info.put("level", spell.getLevel());
            info.put("concentration", spell.isConcentration());
            result.add(info);
        }

        return result;
    }

}

