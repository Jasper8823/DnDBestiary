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
    console.log(DmgList);

    const item = {
        name: document.getElementById("nameLabel").value,

        description: document.getElementById("description").value,
        configurable: document.getElementById("Configurable").checked,
        item_type_name: document.getElementById("typeLabel").value,
        rarity_name: document.getElementById("rarityLabel").value,
        subtype: document.getElementById("subTypeLabel").value,

        DamageTypes: DmgList,
        StatusList: StatusList,
    }

    itemPush(item);
    StatusList = [];
    DmgList = [];
    document.getElementById("nameLabel").value = '';
    document.getElementById("description").value = '';
    document.getElementById("Configurable").checked = false;
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

document.getElementById("GetMonster").addEventListener('click', () => {
    window.location.href = 'fillDBMonster';
});
document.getElementById("GetAbility").addEventListener('click', () => {
    window.location.href = 'fillDBAbility';
});
document.getElementById("GetSpell").addEventListener('click', () => {
    window.location.href = 'fillDBSpell';
});
