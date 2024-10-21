
document.getElementById("SubmitButton").addEventListener('click', () => {
    const ability = {
        name: document.getElementById("nameLabel").value,
        description: document.getElementById("description").value,
        level: parseInt(document.getElementById("levelLabel").value),
        className: document.getElementById("ClassLabel").value,
    };

    abilityPush(ability);

    // Clear form fields after submission
    document.getElementById("nameLabel").value = '';
    document.getElementById("description").value = '';
});

function abilityPush(ability) {
    fetch('http://localhost:8080/classAbilityPush', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(ability, null, 2),
    })
}

document.getElementById("GetMonster").addEventListener('click', () => {
    window.location.href = 'fillDBMonster';
});
document.getElementById("GetSpell").addEventListener('click', () => {
    window.location.href = 'fillDBSpell';
});
document.getElementById("GetItem").addEventListener('click', () => {
    window.location.href = 'fillDBItem';
});
