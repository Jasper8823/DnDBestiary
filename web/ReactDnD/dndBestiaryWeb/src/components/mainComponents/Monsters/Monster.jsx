import { useParams } from 'react-router-dom';
import style from './monsters.module.css'
import StatBox from './StatBox';
import { useEffect, useState } from 'react';

function Monster(){
    const {id} = useParams();
    const [monster, setMonster] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/getMonster?id=${id}`)
            .then(response => response.json())
            .then(data => setMonster(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    if (!monster) return <p>Loading monster...</p>;

    let safe_bonus = '';
    let dmgV = '';
    let dmgR = '';
    let dmgI = '';
    let habitats = '';

    monster.habitats.forEach(hab =>{
        habitats += `${hab}, `
    })
    habitats = habitats.substring(0, habitats.length-2);

    if(monster.strength_bonus>0){
        safe_bonus += `Str <b>+${monster.strength_bonus}</b> `
    }
    if(monster.dexterity_bonus>0){
        safe_bonus += `Dxt <b>+${monster.dexterity_bonus}</b> `
    }

    if(monster.bodybuild_bonus>0){
        safe_bonus += `BB <b>+${monster.bodybuild_bonus}</b> `
    }

    if(monster.intelligence_bonus>0){
        safe_bonus += `Int <b>+${monster.intelligence_bonus}</b> `
    }

    if(monster.wisdom_bonus>0){
        safe_bonus += `Wis <b>+${monster.wisdom_bonus}</b> `
    }

    if(monster.charisma_bonus>0){
        safe_bonus += `Chr <b>+${monster.charisma_bonus}</b>`
    }

    if(monster.vulnerabilityList.size !=0){
        monster.vulnerabilityList.forEach(vulner => {
            dmgV+= `${vulner}, `
        });
        dmgV = dmgV.substring(0, dmgV.length-2);
    }

    if(monster.resistanceList.size !=0){
        monster.resistanceList.forEach(resist => {
            dmgR+= `${resist}, `
        });
        dmgR = dmgR.substring(0, dmgR.length-2);
    }

    if(monster.immunityStatusList.size !=0){
        monster.immunityStatusList.forEach(immunity => {
            dmgI+= `${immunity}, `
        });
        dmgI = dmgI.substring(0, dmgI.length-2);
    }

    let actions = [];
    let legActions = [];

    monster.actions.forEach(action => {
        if(action.is_legendary){
            legActions.push(action);
        }else{
            actions.push(action);
        }
    });

    let speeds = `Speed ${monster.walk_speed}, `;

    if(monster.swim_speed!=0){
        speeds += `Swim speed ${monster.swim_speed}, `
    }
    if(monster.fly_speed!=0){
        speeds += `Fly speed ${monster.fly_speed}, `
    }

    speeds = speeds.substring(0, speeds.length-2);


    console.log(actions)

    return(
        <div key={monster.id} className={style.mainBox}>
            <p id={style.monsterName}><b>{monster.name}</b></p>
            <div className={style.sizeTypeWVBox}>
                <p><i>{monster.size}</i></p>
                <p>{monster.type}</p>
                <p>{monster.worldView}</p>
            </div>
            <p><b>Armor type</b> {monster.armor_class}</p>
            <p><b>Hit points</b> {monster.calcHP} ({monster.avgHP})</p>
            <p><b>{speeds}</b></p>
            <StatBox name = {"Str"} value = {monster.strength} isGrey = {true}/>
            <StatBox name = {"Dxt"} value = {monster.dexterity} isGrey = {false}/>
            <StatBox name = {"BB"} value = {monster.bodybuild} isGrey = {true}/>
            <StatBox name = {"Int"} value = {monster.intelligence} isGrey = {false}/>
            <StatBox name = {"Wis"} value = {monster.wisdom} isGrey = {true}/>
            <StatBox name = {"Chr"} value = {monster.charisma} isGrey = {false}/>
            {safe_bonus.length != 0 &&   <p dangerouslySetInnerHTML={{ __html: `<b>Saving Throws</b> ${safe_bonus}` }} />}
            {dmgV.length != 0 &&   <p><b>Vulnerabilities</b> {dmgV} </p>}
            {dmgR.length != 0 &&   <p><b>Resistances</b> {dmgR} </p>}
            {dmgI.length != 0 &&   <p><b>Immunities</b> {dmgI} </p>}
            <p><b>Habitats </b>{habitats}</p>
            <p><b>Danger</b> {monster.danger.degree} ({monster.danger.expGain})</p>
            <p><b>Skill bonus +{monster.skill_bonus}</b></p>
            <p className = {style.rows}><b>Features</b></p>
            <p>{monster.features}</p>
            <p className = {style.rows}><b>Actions</b></p>
            {actions.map(action => <p dangerouslySetInnerHTML={{ __html: `<b>${action.action_name}:</b> ${action.information}` }}/>)}
            {legActions.length != 0 && <p className = {style.rows}><b>Legendary Actions</b></p>&& 
            legActions.map(action => <p dangerouslySetInnerHTML={{ __html: `<b>${action.name}:</b> ${action.info}` }}/>)}
            <p className = {style.rows}><b>Description</b></p>
            <p>{monster.description}</p>
        </div>
    )
}

export default Monster;