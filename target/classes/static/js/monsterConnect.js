let damageRes = [];
let damageVul = [];
let damageImm = [];
let topographyAdv = [];
let topographyDAdv = [];
let classAdv = [];
let classDAdv = [];
let locationType = [];
let statusType = [];
let actionList = [];

document.getElementById("RelButtonD").addEventListener('click', () => {
    if(document.getElementById("Resist").checked){
        damageRes.push(document.getElementById("DamgType").value);
    }else if(document.getElementById("Vulner").checked){
        damageVul.push(document.getElementById("DamgType").value);
    }else if(document.getElementById("Vulner").checked){
        damageImm.push(document.getElementById("DamgType").value);
    }
});

document.getElementById("RelButtonT").addEventListener('click', () => {
    if(document.getElementById("TAdv").checked){
        topographyAdv.push(document.getElementById("TopogType").value);
    }else if(document.getElementById("TDAdv").checked){
        topographyDAdv.push(document.getElementById("TopogType").value);
    }
});

document.getElementById("RelButtonS").addEventListener('click', () => {
    statusType.push(document.getElementById("StatType").value)
});

document.getElementById("RelButtonL").addEventListener('click', () => {
    locationType.push(document.getElementById("LocationType").value)
});

document.getElementById("RelButtonC").addEventListener('click', () => {
    if(document.getElementById("CAdv").checked){
        classAdv.push(document.getElementById("ClassRelType").value);
    }else if(document.getElementById("CDAdv").checked){
        classDAdv.push(document.getElementById("ClassRelType").value);
    }
});

document.getElementById("ActionButton").addEventListener('click', () => {
    actionList.push(ActionDTO = {name: document.getElementById("AnameLabel").value === "" ? document.getElementById("AnameType").value : document.getElementById("AnameLabel").value,
                              info: document.getElementById("Ainfo").value,
                              legend: document.getElementById("IsLegend").checked ? "true": "false"});
});



document.getElementById("SubmitButton").addEventListener('click', () => {
    const monster = {
        name: document.getElementById("nameLabel").value,

        strength: document.getElementById("STLabel").value,
        dexterity: document.getElementById("DXLabel").value,
        bodybuild: document.getElementById("BBLabel").value,
        intelligence: document.getElementById("INLabel").value,
        wisdom: document.getElementById("WILabel").value,
        charisma: document.getElementById("CHLabel").value,

        perception: document.getElementById("PCLabel").value,
        armor_class: document.getElementById("ACLabel").value,
        skill_bonus: document.getElementById("SKBLabel").value,

        strength_bonus: document.getElementById("STBLabel").value,
        dexterity_bonus: document.getElementById("DXBLabel").value,
        bodybuild_bonus: document.getElementById("BBBLabel").value,
        intelligence_bonus: document.getElementById("INBLabel").value,
        wisdom_bonus: document.getElementById("WIBLabel").value,
        charisma_bonus: document.getElementById("CHBLabel").value,

        walk_speed: document.getElementById("WalkSpeed").checked ? document.getElementById("WSV").value : 0,
        swim_speed: document.getElementById("SwimSpeed").checked ? document.getElementById("SSV").value : 0,
        fly_speed: document.getElementById("FlySpeed").checked ? document.getElementById("FSV").value : 0,

        numberofdice: document.getElementById("NDLabel").value,
        dicetype: document.getElementById("DVLabel").value,
        passivebonus: document.getElementById("PBLabel").value,

        worldview: document.getElementById("WWLabel").value,
        type: document.getElementById("typeLabel").value,
        size: document.getElementById("SizeLabel").value,
        danger: document.getElementById("DangerLabel").value,

        features: document.getElementById("features").value,
        description: document.getElementById("description").value,

        resistanceList: damageRes,
        vulnerabilityList: damageVul,
        immunityList: damageImm,

        topographyAdvList: topographyAdv,
        topographyWeakList: topographyDAdv,

        classAdvList: classAdv,
        classWeakList: classDAdv,

        immunityStatusList: statusType,

        habitats: locationType,

        actions: actionList
    }
    monsterPush(monster);
});


function monsterPush(monster) {

    fetch('http://localhost:8080/monsterPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(monster,null,2),
    })

}