package com.example.DnDProject.Services;


import com.example.DnDProject.Exceptions.EntityNotFoundException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

        monster.setDanger(dangerRepo.findById(dto.getDanger()).orElseThrow(() ->
                new EntityNotFoundException("Danger not found with ID: " + dto.getDanger())));
        monster.setType(typeRepo.findById(dto.getType()).orElseThrow(() ->
                new EntityNotFoundException("Type not found with ID: " + dto.getType())));
        monster.setSize(sizeRepo.findById(dto.getSize()).orElseThrow(() ->
                new EntityNotFoundException("Size not found with ID: " + dto.getSize())));
        monster.setWorldview(worldviewRepo.findById(dto.getWorldview()).orElseThrow(() ->
                new EntityNotFoundException("Worldview not found with ID: " + dto.getWorldview())));

        monster.setResistanceList(dfu.fetchList(dto.getResistanceList(),damageTypeRepo));
        monster.setVulnerabilityList(dfu.fetchList(dto.getVulnerabilityList(),damageTypeRepo));
        monster.setImmunityList(dfu.fetchList(dto.getImmunityList(),damageTypeRepo));

        monster.setImmunityStatusList(dfu.fetchList(dto.getImmunityStatusList(),statusRepo));

        monster.setHabitats(dfu.fetchList(dto.getHabitats(),locationRepo));

        monster.setTopographyAdvList(dfu.fetchList(dto.getTopographyAdvList(),topographyRepo));
        monster.setTopographyWeakList(dfu.fetchList(dto.getTopographyWeakList(),topographyRepo));

        monster.setClassAdvList(dfu.fetchList(dto.getClassAdvList(),classRepo));
        monster.setClassWeakList(dfu.fetchList(dto.getClassWeakList(),classRepo));

        repo.save(monster);
        dfu.fetchActionsList(dto.getActions(),monster);

    }
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
        spell.setSpellType(spellTypeRepo.findById(dto.getSpellTypename()).orElseThrow(() ->
                new EntityNotFoundException("Spell Type not found with name: " + dto.getSpellTypename())));

        spell.setSpell_statusList(dfu.fetchList(dto.getStatus_names(),statusRepo));
        spell.setSpell_damTypeList(dfu.fetchList(dto.getDamageType_names(),damageTypeRepo));
        spell.setSpell_classList(dfu.fetchList(dto.getClass_names(),classRepo));

        spellRepo.save(spell);
    }
    public void saveItem(ItemDTO dto){
        Item item = new Item();

        item.setName(dto.getName());
        item.setConfigurable(dto.isConfigurable());
        item.setDescription(dto.getDescription());

        item.setRarity(rarityRepo.findById(dto.getRarity_name()).orElseThrow(() ->
                new EntityNotFoundException("Rarity not found with name: " + dto.getRarity_name())));
        item.setItemType(itemTypeRepo.findById(dto.getItem_type_name()).orElseThrow(() ->
                new EntityNotFoundException("Item Type not found with name: " + dto.getItem_type_name())));

        item.setItem_charList(null);

        System.out.println(dto.getItem_type_name()+" "+dto.getSubtype());

        item = dfu.setItemSubType(item, dto.getItem_type_name(), dto.getSubtype());

        item.setItem_statusList(dfu.fetchList(dto.getStatusList(),statusRepo));
        item.setItem_damTypeList(dfu.fetchList(dto.getDamageTList(),damageTypeRepo));
        itemRepo.save(item);
    }

    public void saveClassAbility(ClassAbilityDTO dto) {
        ClassAbility ability = new ClassAbility();
        ability.setName(dto.getName());
        ability.setLevel(dto.getLevel());
        ability.setDescription(dto.getDescription());

        ability.setCharClass(classRepo.findById(dto.getClassName()).orElseThrow(() ->
                new EntityNotFoundException("Character Class not found with name: " + dto.getClassName())));
        cabilityRepo.save(ability);
    }

}

