package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Monster.MonsterAttributes.Danger;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Class.ClassAbilityRepository;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import com.example.DnDProject.Repositories.Item.ItemTypeRepository;
import com.example.DnDProject.Repositories.Item.RarityRepository;
import com.example.DnDProject.Repositories.Item.SubTypeRepository;
import com.example.DnDProject.Repositories.Monster.Action.ActionRepository;
import com.example.DnDProject.Repositories.Monster.DamageType.DamageTypeRepository;
import com.example.DnDProject.Repositories.Monster.Location.LocationRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.DangerRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.SizeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.TypeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.WorldviewRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.Repositories.Monster.StatusSens.StatusRepository;
import com.example.DnDProject.Repositories.Monster.Topography.TopographyRepository;
import com.example.DnDProject.Repositories.MtoMConnections.MonsterActionRepository;
import com.example.DnDProject.Repositories.Spell.SpellRepository;
import com.example.DnDProject.Repositories.Spell.SpellTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@Validated
public class DataService {

    @Autowired
    private MonsterRepository repo;

    //Monster attributes repositories
    @Autowired
    private DangerRepository dangerRepo;
    @Autowired
    private SizeRepository sizeRepo;
    @Autowired
    private TypeRepository typeRepo;
    @Autowired
    private WorldviewRepository worldviewRepo;

    //Action repositories
    @Autowired
    private ActionRepository actionRepo;
    @Autowired
    private MonsterActionRepository monsterActionRepo;

    //Status and damagetype repositories
    @Autowired
    private StatusRepository statusRepo;
    @Autowired
    private DamageTypeRepository damageTypeRepo;

    //Location repositories
    @Autowired
    private LocationRepository locationRepo;
    @Autowired
    private TopographyRepository topographyRepo;

    //Class repositories
    @Autowired
    private CharacterClassRepository classRepo;
    @Autowired
    private ClassAbilityRepository cabilityRepo;

    //Spell repositories
    @Autowired
    private SpellTypeRepository spellTypeRepo;
    @Autowired
    private SpellRepository spellRepo;

    //Item repositories
    @Autowired
    private SubTypeRepository subTypeRepo;
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private RarityRepository rarityRepo;
    @Autowired
    private ItemTypeRepository itemTypeRepo;

    // Util Methods
    @Autowired
    private DataFetchUtil dfu;

    @Transactional
    public Map<String, Object> monsterInfo(int id) {
        Monster monster = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Monster not found with id: " + id));


        Map<String, Object> monsterInfo = new HashMap<>();
        monsterInfo.put("name", monster.getName());
        monsterInfo.put("danger", monster.getDanger().getDegree());
        monsterInfo.put("size", monster.getSize().getName());
        monsterInfo.put("worldView", monster.getWorldview().getName());
        monsterInfo.put("type", monster.getType().getName());
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


}

