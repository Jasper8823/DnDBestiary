document.getElementById("SubmitButton").addEventListener('click', () => {
    const item = {
        name: document.getElementById("nameLabel").value,

        description: document.getElementById("description").value,
        configurable: document.getElementById("Configurable").checked,
        item_type_name: document.getElementById("typeLabel").value,
        rarity_name: document.getElementById("rarityLabel").value,
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