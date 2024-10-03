let damageType = new Array();
let topographyType = new Array();
let classType = new Array();
let locationType = new Array();
let statusType = new Array();
let actionList = new Array();

document.getElementById("RelButtonD").addEventListener('click', () => {
    if(document.getElementById("Resist").checked){
        damageType.push("+"+document.getElementById("DamgType").value);
    }else if(document.getElementById("Vulner").checked){
        damageType.push("-"+document.getElementById("DamgType").value);
    }else{
        damageType.push("0"+document.getElementById("DamgType").value);
    }
});

document.getElementById("RelButtonT").addEventListener('click', () => {
    if(document.getElementById("TAdv").checked){
        damageType.push("+"+document.getElementById("TopogType").value);
    }else{
        damageType.push("-"+document.getElementById("TopogType").value);
    }
});

document.getElementById("RelButtonS").addEventListener('click', () => {
    topographyType.push(document.getElementById("StatType").value)
});

document.getElementById("RelButtonL").addEventListener('click', () => {
    topographyType.push(document.getElementById("LocationType").value)
});

document.getElementById("RelButtonC").addEventListener('click', () => {
    if(document.getElementById("CAdv").checked){
        damageType.push("+"+document.getElementById("ClassRelType").value);
    }else{
        damageType.push("-"+document.getElementById("ClassRelType").value);
    }
});

document.getElementById("ActionButton").addEventListener('click', () => {
    actionList.push(action = {aname: document.getElementById("AnameLabel").value === "" ? document.getElementById("AnameType").value : document.getElementById("AnameLabel").value,
                              info: document.getElementById("Ainfo").value,
                              Legend: document.getElementById("IsLegend").chacked ? true: false});
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
        armorclass: document.getElementById("ACLabel").value,
        skillB: document.getElementById("SKBLabel").value,

        strengthB: document.getElementById("STBLabel").value,
        dexterityB: document.getElementById("DXBLabel").value,
        bodybuildB: document.getElementById("BBBLabel").value,
        intelligenceB: document.getElementById("INBLabel").value,
        wisdomB: document.getElementById("WIBLabel").value,
        charismaB: document.getElementById("CHBLabel").value,

        walkspeed: document.getElementById("WalkSpeed").checked ? document.getElementById("WSV").value : 0,
        swimspeed: document.getElementById("SwimSpeed").checked ? document.getElementById("SSV").value : 0,
        flyspeed: document.getElementById("FlySpeed").checked ? document.getElementById("FSV").value : 0,

        numberofdice: document.getElementById("NDLabel").value,
        dicetype: document.getElementById("DVLabel").value,
        passivebonus: document.getElementById("PBLabel").value,

        worldView: document.getElementById("WWLabel").value,
        type: document.getElementById("typeLabel").value,
        size: document.getElementById("SizeLabel").value,
        danger: document.getElementById("DangerLabel").value,

        features: document.getElementById("features").value,
        description: document.getElementById("description").value,

        damageType: damageType,
        topographyType: topographyType,
        classType: classType, 
        statusType: statusType,
        locationType: locationType,

        actions: actionList
    }


    console.log(monster.name);
    console.log(monster.strength);
    console.log(monster.dexterity);
    console.log(monster.bodybuild);
    console.log(monster.intelligence);
    console.log(monster.wisdom);
    console.log(monster.charisma);
    onSubmitButtonClick(monster.name);

    monsterPush(monster);
});

function onSubmitButtonClick(name) {

    fetch('http://localhost:8080/hello', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain',
        },
        body: name,
    })

}

function monsterPush(monster) {

    fetch('http://localhost:8080/monsterPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain',
        },
        body: monster,
    })

}