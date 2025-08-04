package com.example.DnDProject.Cache;

import com.example.DnDProject.Entities.Character.Character;
import com.example.DnDProject.DTOs.CharacterDTO;
import com.example.DnDProject.Repositories.BackStory.BackStoryRepository;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Service
public class CharacterCache {

    @Autowired
    private CharacterClassRepository charClassRepo;
    @Autowired
    private BackStoryRepository backstoryRepo;
    @Autowired
    private RaceRepository raceRepo;

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

        character.setName(dto.getName());
        character.setLevel(dto.getLevel());
        if(dto.getLevel()>2){
            character.setCharClass(charClassRepo.findById(dto.getArchetype()).orElse(null));
        }else{
            character.setCharClass(charClassRepo.findById(dto.getClazz()).orElse(null));
        }
        character.setBackstory(backstoryRepo.findById(dto.getBackstory()).orElse(null));
        character.setRace(raceRepo.findById(dto.getRace()).orElse(null));

        character.setStrength(dto.getStats()[0]);
        character.setDexterity(dto.getStats()[1]);
        character.setConstitution(dto.getStats()[2]);
        character.setIntelligence(dto.getStats()[3]);
        character.setWisdom(dto.getStats()[4]);
        character.setCharisma(dto.getStats()[5]);

        character.setSpeed(getSpeed(dto.getClazz(),dto.getLevel()));
        character.setHp(getHp(dto.getClazz(), dto.getLevel(),dto.getStats()[2]));

        String cacheId = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plusMillis(ttlMillis);
        cache.put(cacheId, new CacheEntry(character, expiresAt));

        return cacheId;
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

