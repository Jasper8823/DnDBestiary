let classSpellList = [];
let StatusList = [];
let DmgList = [];


document.getElementById("RelButtonCSR").addEventListener('click', () => {
    classSpellList = [];
});

document.getElementById("RelButtonCSS").addEventListener('click', () => {
    console.log('ClassSpell:'+classSpellList);
});

document.getElementById("RelButtonCS").addEventListener('click', () => {
    statusType.push(document.getElementById("ClassSpell").value)
});

document.getElementById("RelButtonDR").addEventListener('click', () => {
    DmgList = [];
});

document.getElementById("RelButtonDS").addEventListener('click', () => {
    console.log('Damage:'+DmgList);
});

document.getElementById("RelButtonD").addEventListener('click', () => {
    DmgList.push(document.getElementById("DamageType").value)
});

document.getElementById("RelButtonSR").addEventListener('click', () => {
    StatusList = [];
});

document.getElementById("RelButtonSS").addEventListener('click', () => {
    console.log('Status:'+StatusList);
});

document.getElementById("RelButtonS").addEventListener('click', () => {
    StatusList.push(document.getElementById("StatusType").value)
});

document.getElementById("SubmitButton").addEventListener('click', () => {
    const spell = {
        name: document.getElementById("nameLabel").value,

        description: document.getElementById("description").value,
        level: document.getElementById("levelLabel").value.parseInt(),
        spellTypename: document.getElementById("TypeLabel").value,
        duration: document.getElementById("durationLabel").value,
        concentDura: document.getElementById("concDurationLabel").value,
        distance: document.getElementById("distanceLabel").value.parseInt(),
        concentration: document.getElementById("concentration").checked,
        target: document.getElementById("targetLabel").value,
        prepareMoves: document.getElementById("prepMovesLabel").value.parseInt(),

        spell_classList: classSpellList,

        DamageTList: DmgList,
        StatusList: StatusList,
    }
    spellPush(spell);
});


function spellPush(spell) {

    fetch('http://localhost:8080/spellPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(spell,null,2),
    })
}


document.getElementById("GetSpell").addEventListener( 'click',() =>{
    fetch(`http://localhost:8080/Spell`, {
        method: 'GET',
    })
})

document.getElementById("GetMonster").addEventListener( 'click',() =>{
    fetch(`http://localhost:8080/Monster`, {
        method: 'GET',
    })
})

document.getElementById("GetItem").addEventListener( 'click',() =>{
    fetch(`http://localhost:8080/Item`, {
        method: 'GET',
    })
})

document.getElementById("GetAbility").addEventListener( 'click',() =>{
    fetch(`http://localhost:8080/Ability`, {
        method: 'GET',
    })
})