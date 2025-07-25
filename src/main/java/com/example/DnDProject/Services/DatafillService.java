package com.example.DnDProject.Services;


import com.example.DnDProject.UtilMethods.DataFetchUtil;
import com.example.DnDProject.DTOs.*;
import com.example.DnDProject.Entities.Class.ClassAbility;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Class.ClassAbilityRepository;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import com.example.DnDProject.Repositories.Item.ItemTypeRepository;
import com.example.DnDProject.Repositories.Item.RarityRepository;
import com.example.DnDProject.Repositories.Monster.DamageType.DamageTypeRepository;
import com.example.DnDProject.Repositories.Monster.Location.LocationRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.DangerRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.SizeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.TypeRepository;
import com.example.DnDProject.Repositories.Monster.MonsterAttributes.WorldviewRepository;
import com.example.DnDProject.Repositories.Monster.MonsterRepository;
import com.example.DnDProject.Repositories.Monster.StatusSens.StatusRepository;
import com.example.DnDProject.Repositories.Monster.Topography.TopographyRepository;
import com.example.DnDProject.Repositories.Spell.SpellRepository;
import com.example.DnDProject.Repositories.Spell.SpellTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class DatafillService {

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
    public void saveMonster(MonsterDTO dto){
        Monster monster = new Monster();

        monster.setName(dto.getName());
        monster.setArmor_class(dto.getArmor_class());

        // Set speed fields
        monster.setSpeed(dto.getWalk_speed());
        monster.setSwim_speed(dto.getSwim_speed());
        monster.setFly_speed(dto.getFly_speed());

        // Set HP fields
        monster.setAvg_HP(dfu.calculateAvgHP(dto.getNumberofdice(), dto.getDicetype(), dto.getPassivebonus()));
        monster.setCalc_HP(dfu.formatHPCalculation(dto.getNumberofdice(), dto.getDicetype(), dto.getPassivebonus()));

        // Set monster attributes
        monster.setStrength(dto.getStrength());
        monster.setDexterity(dto.getDexterity());
        monster.setCharisma(dto.getCharisma());
        monster.setIntelligence(dto.getIntelligence());
        monster.setWisdom(dto.getWisdom());
        monster.setConstitution(dto.getBodybuild());

        // Set other fields
        monster.setPerception(dto.getPerception());
        monster.setSkill_bonus(dto.getSkill_bonus());

        // Set bonuses
        monster.setStrength_bonus(dto.getStrength_bonus());
        monster.setDexterity_bonus(dto.getDexterity_bonus());
        monster.setCharisma_bonus(dto.getCharisma_bonus());
        monster.setIntelligence_bonus(dto.getIntelligence_bonus());
        monster.setWisdom_bonus(dto.getWisdom_bonus());
        monster.setConstitution_bonus(dto.getBodybuild_bonus());

        // Set features and description
        monster.setFeatures(dto.getFeatures());
        monster.setDescription(dto.getDescription());

        monster.setDanger(dangerRepo.findById(dto.getDanger()).get());
        monster.setType(dfu.fetchEntity(typeRepo,dto.getType()));
        monster.setSize(dfu.fetchEntity(sizeRepo,dto.getSize()));
        monster.setWorldview(dfu.fetchEntity(worldviewRepo,dto.getWorldview()));

        monster.setResistanceList(dfu.fetchList(dto.getResistanceList(),damageTypeRepo));
        monster.setVulnerabilityList(dfu.fetchList(dto.getVulnerabilityList(),damageTypeRepo));
        monster.setImmunityList(dfu.fetchList(dto.getImmunityList(),damageTypeRepo));

        monster.setImmunityStatusList(dfu.fetchList(dto.getImmunityStatusList(),statusRepo));

        monster.setHabitats(dfu.fetchList(dto.getHabitats(),locationRepo));

        monster.setTopographyAdvList(dfu.fetchList(dto.getTopographyAdvList(),topographyRepo));
        monster.setTopographyWeakList(dfu.fetchList(dto.getTopographyWeakList(),topographyRepo));

        monster.setClassAdvList(dfu.fetchList(dto.getClassAdvList(),classRepo));
        monster.setClassWeakList(dfu.fetchList(dto.getClassWeakList(),classRepo));

        repo.saveAndFlush(monster);
        dfu.fetchActionsList(dto.getActions(),monster);

    }
    @Transactional
    public void saveSpell(SpellDTO dto){
        Spell spell = new Spell();

        spell.setName(dto.getName());

        spell.setConcentDura(dto.getConcentDura());
        spell.setConcentration(dto.isConcentration());
        spell.setLevel(dto.getLevel());
        spell.setDistance(dto.getDistance());
        spell.setTarget(dto.getTarget());
        spell.setDescription(dto.getDescription());
        spell.setPrepareMoves(dto.getPrepareMoves());
        spell.setDuration(dto.getDuration());

        spell.setSpell_classList(dfu.fetchList(dto.getSpell_classList(),classRepo));
        spell.setSpellType(dfu.fetchEntity(spellTypeRepo,dto.getSpellTypename()));

        spell.setSpell_statusList(dfu.fetchList(dto.getStatus_names(),statusRepo));
        spell.setSpell_classList(dfu.fetchList(dto.getClass_names(),classRepo));

        spellRepo.saveAndFlush(spell);
        dfu.fetchDamageTypesList(dto.getDamageType_names(),null,spell);
    }
    @Transactional
    public void saveItem(ItemDTO dto){
        Item item = new Item();

        item.setName(dto.getName());
        item.setConfigurable(dto.isConfigurable());
        item.setDescription(dto.getDescription());

        item.setRarity(dfu.fetchEntity(rarityRepo,dto.getRarity_name()));
        item.setItemType(dfu.fetchEntity(itemTypeRepo,dto.getItem_type_name()));

        item.setItem_charList(null);

        item = dfu.setItemSubType(item, dto.getItem_type_name(), dto.getSubtype());

        item.setItem_statusList(dfu.fetchList(dto.getStatusList(),statusRepo));
        itemRepo.saveAndFlush(item);
        dfu.fetchDamageTypesList(dto.getDamageTypes(),item,null);

    }
    @Transactional
    public void saveClassAbility( ClassAbilityDTO dto) {
        ClassAbility ability = new ClassAbility();
        ability.setName(dto.getName());
        ability.setLevel(dto.getLevel());
        ability.setDescription(dto.getDescription());

        ability.setCharClass(dfu.fetchEntity(classRepo,dto.getClassName()));
        cabilityRepo.save(ability);
    }

}

