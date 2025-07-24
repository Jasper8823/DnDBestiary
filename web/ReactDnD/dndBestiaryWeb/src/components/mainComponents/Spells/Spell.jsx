import { useParams } from 'react-router-dom';
import style from './spells.module.css'
import { useEffect, useState } from 'react';

function Spell(){
    const {id} = useParams();
    const [spell, setSpell] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/getSpell?id=${id}`)
            .then(response => response.json())
            .then(data => setSpell(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    if (!spell) return <p>Loading spell...</p>;

    let statuses = '';

    if(spell.status_names.length !==0){
        spell.status_names.forEach(status => {
            statuses+= `${status}, `
        });
        statuses = statuses.substring(0, statuses.length-2);
    }

    let classes = '';

    if(spell.class_names.length !==0){
        spell.class_names.forEach(classC => {
            classes+= `${classC}, `
        });
        classes = classes.substring(0, classes.length-2);
    }

    let damageDeal = '';
    
    if(spell.DamageTypes.length !==0){
        spell.DamageTypes.forEach(damageDeal => {
            damageDeal+= `${damageDeal}, `
        });
        damageDeal = damageDeal.substring(0, damageDeal.length-2);
    }

    return(
        <div key={spell.id} className={style.mainBox}>
            <p id={style.spellName}><b>{spell.name}</b></p>
            <div className={style.sizeTypeWVBox}>
                <p>{spell.level} level, {/*{spell.subtype}*/}</p>
            </div>
            {spell.prepareMoves > 0 && <p><b>Prepare moves:</b> {spell.prepareMoves}</p>}
            <p><b>Distance:</b> {spell.distance} feet</p>
            {isNaN(spell.duration) && <p><b>Duration:</b> {spell.duration}</p>}
            {classes.length !== 0 && <p>Statuses: {classes}</p>}
            {statuses.length !== 0 && <p>Statuses: {statuses}</p>}
            {damageDeal.length !== 0 && <p>Damage Types: {damageDeal}</p>}
            {isNaN(spell.description) && <p className = {style.rows}><b>Description</b><br/></p>}
            <p>{spell.description}</p>
        </div>
    )
}

export default Spell;