package com.example.DnDProject.Services;

import com.example.DnDProject.DTOs.*;
import com.example.DnDProject.Entities.Class.ClassAbility;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Item.ItemType;
import com.example.DnDProject.Entities.Item.Rarity;
import com.example.DnDProject.Entities.Monster.Action.Action;
import com.example.DnDProject.Entities.Monster.Monster;
import com.example.DnDProject.Entities.MtoMConnections.MonsterAction;
import com.example.DnDProject.Entities.Spell.Spell;
import com.example.DnDProject.Repositories.Class.CharacterClassRepository;
import com.example.DnDProject.Repositories.Class.ClassAbilityRepository;
import com.example.DnDProject.Repositories.Item.ItemRepository;
import com.example.DnDProject.Repositories.Item.ItemTypeRepository;
import com.example.DnDProject.Repositories.Item.RarityRepository;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private ItemRepository itemRepo;
    @Autowired
    private RarityRepository rarityRepo;
    @Autowired
    private ItemTypeRepository itemTypeRepo;


    public void saveMonster(MonsterDTO dto){
        Monster monster = new Monster();

        monster.setName(dto.getName());
        monster.setArmor_class(dto.getArmor_class());

        // Set speed fields
        monster.setSpeed(dto.getWalk_speed());
        monster.setSwim_speed(dto.getSwim_speed());
        monster.setFly_speed(dto.getFly_speed());

        // Set HP fields
        monster.setAvg_HP((dto.getNumberofdice()*dto.getDicetype())/2+dto.getPassivebonus());
        monster.setCalc_HP(String.valueOf(dto.getNumberofdice()).concat("D").concat(String.valueOf(dto.getDicetype()))
                +" + "+dto.getPassivebonus());

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
        monster.setType(typeRepo.findById(dto.getType()).get());
        monster.setSize(sizeRepo.findById(dto.getSize()).get());
        monster.setWorldview(worldviewRepo.findById(dto.getWorldview()).get());

        monster.setResistanceList(fetchList(dto.getResistanceList(),damageTypeRepo));
        monster.setVulnerabilityList(fetchList(dto.getVulnerabilityList(),damageTypeRepo));
        monster.setImmunityList(fetchList(dto.getImmunityList(),damageTypeRepo));

        monster.setImmunityStatusList(fetchList(dto.getImmunityStatusList(),statusRepo));

        monster.setHabitats(fetchList(dto.getHabitats(),locationRepo));

        monster.setTopographyAdvList(fetchList(dto.getTopographyAdvList(),topographyRepo));
        monster.setTopographyWeakList(fetchList(dto.getTopographyWeakList(),topographyRepo));

        monster.setClassAdvList(fetchList(dto.getClassAdvList(),classRepo));
        monster.setClassWeakList(fetchList(dto.getClassWeakList(),classRepo));

        repo.save(monster);
        fetchActionsList(dto.getActions(),monster);

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

        spell.setSpell_classList(fetchList(dto.getSpell_classList(),classRepo));
        spell.setSpellType(spellTypeRepo.findById(dto.getSpellTypename()).get());

        spellRepo.save(spell);
    }
    public void saveItem(ItemDTO dto){
        Item item = new Item();

        item.setName(dto.getName());
        item.setConfigurable(dto.isConfigurable());
        item.setDescription(dto.getDescription());

        item.setRarity(rarityRepo.findById(dto.getRarityName()).get());
        item.setItemType(itemTypeRepo.findById(dto.getItemTypeName()).get());

        itemRepo.save(item);
    }

    public void saveClassAbility(ClassAbilityDTO dto) {
        ClassAbility ability = new ClassAbility();
        ability.setName(dto.getName());
        ability.setLevel(dto.getLevel());
        ability.setDescription(dto.getDescription());

        ability.setCharClass(classRepo.findById(dto.getClassName()).get());

        cabilityRepo.save(ability);
    }

    private <T> List<T> fetchList(List<String> ids, JpaRepository repository) {
        List<T> resultList = new ArrayList<>();
        for (String id : ids) {
            Optional<T> optionalEntity = repository.findById(id);
            optionalEntity.ifPresent(resultList::add);
        }
        return resultList;
    }

    private void fetchActionsList(List<ActionDTO> actions, Monster monster) {
        for (ActionDTO actionDTO : actions) {
            MonsterAction monsterAction = new MonsterAction();
            monsterAction.setInformation(actionDTO.getInfo());
            monsterAction.setLegendary(actionDTO.getLegend());

            if(!actionRepo.existsById(actionDTO.getName())) {
                Action action = new Action();
                action.setName(actionDTO.getName());
                actionRepo.save(action);
                monsterAction.setAction(action);
            }else{
                monsterAction.setAction(actionRepo.findById(actionDTO.getName()).get());
            }
            monsterAction.setMonster(monster);
            monsterActionRepo.save(monsterAction);
        }
    }

}

