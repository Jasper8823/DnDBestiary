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


document.getElementById("RelButtonRD").addEventListener('click', () => {
    damageRes = [];
    damageImm = [];
    damageVul = [];
});

document.getElementById("RelButtonSD").addEventListener('click', () => {
    console.log('Imm:'+damageImm);
    console.log('Vul:'+damageVul);
    console.log('Rea:'+damageRes);
});

document.getElementById("RelButtonD").addEventListener('click', () => {
    if(document.getElementById("Resist").checked){
        damageRes.push(document.getElementById("DamgType").value);
    }else if(document.getElementById("Vulner").checked){
        damageVul.push(document.getElementById("DamgType").value);
    }else if(document.getElementById("ImmunD").checked){
        damageImm.push(document.getElementById("DamgType").value);
    }
});




document.getElementById("RelButtonRT").addEventListener('click', () => {
    topographyAdv = [];
    topographyDAdv = [];
});

document.getElementById("RelButtonST").addEventListener('click', () => {
    console.log('Topog adv:'+topographyAdv);
    console.log('Topog Dadv:'+topographyDAdv);
});

document.getElementById("RelButtonT").addEventListener('click', () => {
    if(document.getElementById("TAdv").checked){
        topographyAdv.push(document.getElementById("TopogType").value);
    }else if(document.getElementById("TDAdv").checked){
        topographyDAdv.push(document.getElementById("TopogType").value);
    }
});




document.getElementById("RelButtonRS").addEventListener('click', () => {
    statusType = [];
});

document.getElementById("RelButtonSS").addEventListener('click', () => {
    console.log('Status:'+statusType);
});

document.getElementById("RelButtonS").addEventListener('click', () => {
    statusType.push(document.getElementById("StatType").value)
});




document.getElementById("RelButtonRL").addEventListener('click', () => {
    locationType = [];
});

document.getElementById("RelButtonSL").addEventListener('click', () => {
    console.log('Location:'+locationType);
});

document.getElementById("RelButtonL").addEventListener('click', () => {
    locationType.push(document.getElementById("LocationType").value)
});



document.getElementById("RelButtonRC").addEventListener('click', () => {
    classAdv = [];
    classDAdv = [];
});

document.getElementById("RelButtonSC").addEventListener('click', () => {
    console.log('Class adv:'+classAdv);
    console.log('Class Dadv:'+classDAdv);
});

document.getElementById("RelButtonC").addEventListener('click', () => {
    if(document.getElementById("CAdv").checked){
        classAdv.push(document.getElementById("ClassRelType").value);
    }else if(document.getElementById("CDAdv").checked){
        classDAdv.push(document.getElementById("ClassRelType").value);
    }
});



document.getElementById("ActionButtonR").addEventListener('click', () => {
    actionList=[];
});

document.getElementById("ActionButtonS").addEventListener('click', () => {
    console.log('actions:'+actionList);
});

document.getElementById("ActionButton").addEventListener('click', () => {
    actionList.push(ActionDTO = {name: document.getElementById("AnameLabel").value === "" ? document.getElementById("AnameType").value : document.getElementById("AnameLabel").value,
                              info: document.getElementById("Ainfo").value,
                              legend: document.getElementById("IsLegend").checked});
    document.getElementById("AnameLabel").value = "";
    document.getElementById("Ainfo").value = '';
    document.getElementById("IsLegend").checked = false;
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
        danger: parseInt(document.getElementById("DangerLabel").value),

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
    damageRes = [];
    damageVul = [];
    damageImm = [];
    topographyAdv = [];
    topographyDAdv = [];
    classAdv = [];
    classDAdv = [];
    locationType = [];
    statusType = [];
    actionList = [];
    document.getElementById("nameLabel").value ='';
    document.getElementById("STLabel").value = 10;
    document.getElementById("DXLabel").value = 10;
    document.getElementById("BBLabel").value= 10;
    document.getElementById("INLabel").value= 10;
    document.getElementById("WILabel").value= 10;
    document.getElementById("CHLabel").value= 10;

    document.getElementById("PCLabel").value= 10;
    document.getElementById("ACLabel").value= 10;
    document.getElementById("SKBLabel").value= 0;
    document.getElementById("STBLabel").value= 0;
    document.getElementById("DXBLabel").value= 0;
    document.getElementById("BBBLabel").value= 0;
    document.getElementById("INBLabel").value= 0;
    document.getElementById("WIBLabel").value= 0;
    document.getElementById("CHBLabel").value= 0;

    document.getElementById("WalkSpeed").checked = false;
    document.getElementById("WSV").value = 30;
    document.getElementById("SwimSpeed").checked = false;
    document.getElementById("SSV").value = 30;
    document.getElementById("FlySpeed").checked = false;
    document.getElementById("FSV").value = 30;

    document.getElementById("NDLabel").value = 1;
    document.getElementById("DVLabel").value = 10;
    document.getElementById("PBLabel").value = 5;

    document.getElementById("features").value = '';
    document.getElementById("description").value = '';
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


document.getElementById("GetAbility").addEventListener('click', () => {
    window.location.href = 'fillDBAbility';
});
document.getElementById("GetSpell").addEventListener('click', () => {
    window.location.href = 'fillDBSpell';
});
document.getElementById("GetItem").addEventListener('click', () => {
    window.location.href = 'fillDBItem';
});