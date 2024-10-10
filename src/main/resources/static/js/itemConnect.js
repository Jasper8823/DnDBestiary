let StatusList = [];
let DmgList = [];

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
    console.log(document.getElementById("subTypeLabel").value);
    const item = {
        name: document.getElementById("nameLabel").value,

        description: document.getElementById("description").value,
        configurable: document.getElementById("Configurable").checked,
        itemTypeName: document.getElementById("typeLabel").value,
        rarityName: document.getElementById("rarityLabel").value,
        subType: document.getElementById("subTypeLabel").value,

        DamageTList: DmgList,
        StatusList: StatusList,
    }
    itemPush(item);
});


function itemPush(item) {

    fetch('http://localhost:8080/itemPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(item,null,2),
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