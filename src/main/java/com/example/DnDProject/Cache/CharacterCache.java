package com.example.DnDProject.Cache;

import com.example.DnDProject.DTOs.CharacterDtos.CharSpellDTO;
import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.DTOs.CharacterDtos.CharacterDTO;
import com.example.DnDProject.Entities.Class.CharacterClass;
import com.example.DnDProject.Entities.Class.SpellSlots;
import com.example.DnDProject.Entities.Login.User;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.BackStory.BackStoryRepository;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Login.UserRepository;
import com.example.DnDProject.Repositories.Race.RaceRepository;
import com.example.DnDProject.Services.DatafillService;
import com.example.DnDProject.Services.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CharacterCache {

    @Autowired
    private CharacterClassRepository charClassRepo;
    @Autowired
    private BackStoryRepository backstoryRepo;
    @Autowired
    private RaceRepository raceRepo;
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private UserRepository userRepo;

    private static final Map<String, Integer> maxHP = Map.ofEntries(
            Map.entry("Barbarian", 12), Map.entry("Fighter", 10), Map.entry("Paladin", 10),
            Map.entry("Ranger", 10), Map.entry("Bard", 8), Map.entry("Cleric", 8),
            Map.entry("Druid", 8), Map.entry("Monk", 8), Map.entry("Rogue", 8),
            Map.entry("Warlock", 8), Map.entry("Artificer", 8), Map.entry("Sorcerer", 6),
            Map.entry("Wizard", 6)
    );
    private static final Map<String, Integer> avgHP = Map.ofEntries(
            Map.entry("Barbarian", 7), Map.entry("Fighter", 6), Map.entry("Paladin", 6),
            Map.entry("Ranger", 6), Map.entry("Bard", 5), Map.entry("Cleric", 5),
            Map.entry("Druid", 5), Map.entry("Monk", 5), Map.entry("Rogue", 5),
            Map.entry("Warlock", 5), Map.entry("Artificer", 5), Map.entry("Sorcerer", 4),
            Map.entry("Wizard", 4)
    );


    private static class CacheEntry {
        private final Character character;
        private final Instant expiresAt;

        public CacheEntry(Character character, Instant expiresAt) {
            this.character = character;
            this.expiresAt = expiresAt;
        }

        public Character getCharacter() {
            return character;
        }

        public Instant getExpiresAt() {
            return expiresAt;
        }
    }

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final long ttlMillis = 15 * 60 * 1000;

    public String createCharacter(CharacterDTO dto) {
        Character character = new Character();
        String className = dto.getClazz().toLowerCase();

        character.setName(dto.getName());
        character.setLevel(dto.getLevel());
        if(dto.getLevel()>2 && (className.equalsIgnoreCase("rogue")
                || className.equalsIgnoreCase("fighter"))){
            character.setCharClass(charClassRepo.findById(dto.getArchetype()).orElse(null));
        }else{
            character.setCharClass(charClassRepo.findById(className).orElse(null));
        }
        character.setBackstory(backstoryRepo.findById(dto.getBackstory()).orElse(null));
        character.setRace(raceRepo.findById(dto.getRace()).orElse(null));

        character.setStrength(dto.getStats()[0]);
        character.setDexterity(dto.getStats()[1]);
        character.setConstitution(dto.getStats()[2]);
        character.setIntelligence(dto.getStats()[3]);
        character.setWisdom(dto.getStats()[4]);
        character.setCharisma(dto.getStats()[5]);

        int userId = sessionManager.getUserId(dto.getSessionId());
        User user = userRepo.findById(userId).orElse(null);
        character.setUser(user);

        character.setSpeed(getSpeed(dto.getClazz(),dto.getLevel()));
        character.setHp(getHp(dto.getClazz(), dto.getLevel(),dto.getStats()[2]));

        String cacheId = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plusMillis(ttlMillis);
        cache.put(cacheId, new CacheEntry(character, expiresAt));

        return cacheId;
    }

    public CharSpellDTO getCharSpells(String id, DatafillService dfs) {
        Character character = getCharacter(id);
        if (character == null) {
            return null;
        }

        int statRaise = (character.getLevel() / 4) * 2;
        CharacterClass clazz = character.getCharClass();

        SpellSlots slots = clazz.getSpellSlotsList().stream()
                .filter(slot -> slot.getLevel() == character.getLevel()
                        && slot.getCharClass().getName().equals(clazz.getName()))
                .findFirst()
                .orElse(null);

        int spells_num = 0;
        int plots_num = 0;
        int maxSpellLevel = 0;

        if (slots != null) {
            spells_num = slots.getSpell_num();
            plots_num = slots.getPlot_num();

            for (int i = 1; i <= 9; i++) {
                int available = switch (i) {
                    case 1 -> slots.getLvl1();
                    case 2 -> slots.getLvl2();
                    case 3 -> slots.getLvl3();
                    case 4 -> slots.getLvl4();
                    case 5 -> slots.getLvl5();
                    case 6 -> slots.getLvl6();
                    case 7 -> slots.getLvl7();
                    case 8 -> slots.getLvl8();
                    case 9 -> slots.getLvl9();
                    default -> 0;
                };
                if (available == 0) break;
                maxSpellLevel = i;
            }
        }

        Map<String, Integer> plots = new LinkedHashMap<>();
        Map<String, Integer> spells = new LinkedHashMap<>();

        int finalMaxSpellLevel = maxSpellLevel;
        clazz.getSpellClassList().stream()
                .sorted(Comparator.comparingInt(Spell::getLevel))
                .forEach(spell -> {
                    int lvl = spell.getLevel();
                    if (lvl == 0) {
                        plots.put(spell.getName(), lvl);
                    } else if (lvl <= finalMaxSpellLevel) {
                        spells.put(spell.getName(), lvl);
                    }
                });

        if (statRaise == 0 && spells_num == 0 && plots_num == 0) {
            dfs.saveCharacter(character, null);
            statRaise = -1;
        }

        List<Integer> stats = List.of(
                character.getStrength(),
                character.getDexterity(),
                character.getConstitution(),
                character.getIntelligence(),
                character.getWisdom(),
                character.getCharisma()
        );

        String race = character.getRace().getName();

        System.out.println(stats);
        System.out.println(statRaise);
        System.out.println(spells_num);
        System.out.println(plots_num);
        System.out.println(race);
        System.out.println(character.getName());

        if(spells.size()<spells_num){
            spells_num = spells.size();
        }

        if(plots.size()<plots_num){
            plots_num = plots.size();
        }

        return new CharSpellDTO(statRaise, spells, plots, spells_num, plots_num, stats, race);
    }



    public Character getCharacter(String id) {
        CacheEntry entry = cache.get(id);
        if (entry == null) return null;

        if (Instant.now().isAfter(entry.getExpiresAt())) {
            cache.remove(id);
            return null;
        }
        return entry.getCharacter();
    }

    int getSpeed(String clazz, int level) {
        if (!clazz.equalsIgnoreCase("Monk")) return 30;

        if (level >= 18) return 60;
        else if (level >= 14) return 55;
        else if (level >= 10) return 50;
        else if (level >= 6) return 45;
        else if (level >= 2) return 40;
        else return 30;
    }
    private int getHp(String clazz, int level, int constitution) {
        Integer max = maxHP.get(clazz);
        Integer avg = avgHP.get(clazz);
        if (max == null || avg == null) return 0;

        int conMod = (constitution-10)/2;
        int hp = max + conMod;

        hp+=(avg+conMod) * (level-1);
        return Math.max(hp, 1);
    }


}

