document.getElementById("SubmitButton").addEventListener('click', () => {
    const ability = {
        name: document.getElementById("nameLabel").value,

        description: document.getElementById("description").value,
        level: document.getElementById("levelLabel").value.parseInt(),
        className: document.getElementById("ClassLabel").value,
    }
    abilityPush(ability);
});


function abilityPush(ability) {

    fetch('http://localhost:8080/abilityPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(ability,null,2),
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
