import { useParams } from 'react-router-dom';
import style from './monsters.module.css'
import StatBox from './StatBox';
import { useEffect, useState } from 'react';
import dice from '../images/dice.png';

function Monster(){
    const {id} = useParams();
    const [monster, setMonster] = useState(null);
    const [calculatedHp, setCalculatedHp] = useState('');

    useEffect(() => {
        fetch(`http://localhost:8080/getMonster?id=${id}`)
            .then(response => response.json())
            .then(data => setMonster(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    function calculateHp(calcHP){
        const values = calcHP.match(/(\d+)d(\d+)\+(\d+)/);
        const numberOfDice = parseInt(values[1]);
        const diceType = parseInt(values[2]);
        const passiveBonus = parseInt(values[3]);
        let output='', sum = passiveBonus, currentValue
        for(let i=0;i<numberOfDice;i++){
            currentValue = Math.floor(Math.random()*diceType+1)
            if(i==0){
                output+= `${currentValue} `
            }else{
                output+= `+ ${currentValue} `
            }
            sum+=currentValue
        }
        output+= `+ ${passiveBonus} = ${sum}`
        setCalculatedHp(output);
    }

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

    if(monster.vulnerabilityList.size !==0){
        monster.vulnerabilityList.forEach(vulner => {
            dmgV+= `${vulner}, `
        });
        dmgV = dmgV.substring(0, dmgV.length-2);
    }

    if(monster.resistanceList.size !==0){
        monster.resistanceList.forEach(resist => {
            dmgR+= `${resist}, `
        });
        dmgR = dmgR.substring(0, dmgR.length-2);
    }

    if(monster.immunityStatusList.size !==0){
        monster.immunityStatusList.forEach(immunity => {
            dmgI+= `${immunity}, `
        });
        dmgI = dmgI.substring(0, dmgI.length-2);
    }

    let actions = [];
    let legActions = [];

    monster.actions.forEach(action => {
        if(action.legend){
            legActions.push(action);
        }else{
            actions.push(action);
        }
    });

    let speeds = `Speed ${monster.walk_speed}, `;

    if(monster.swim_speed!==0){
        speeds += `Swim speed ${monster.swim_speed}, `
    }
    if(monster.fly_speed!==0){
        speeds += `Fly speed ${monster.fly_speed}, `
    }

    speeds = speeds.substring(0, speeds.length-2);

    return(
        <div key={monster.id} className={style.mainBox}>
            <p id={style.monsterName}><b>{monster.name}</b></p>
            <div className={style.sizeTypeWVBox}>
                <p><i>{monster.size}</i></p>
                <p>{monster.type}</p>
                <p>{monster.worldView}</p>
            </div>
            <p><b>Armor type</b> {monster.armor_class}</p>
            <p className={style.hpP}><b>Hit points</b> {monster.avgHP} ({monster.calcHP})</p>
            <img onClick={() => calculateHp(monster.calcHP)} src={dice} className={style.picture} />
            <p className={style.hpP}>{calculatedHp}</p>
            <p><b>{speeds}</b></p>
            <StatBox name = {"Str"} value = {monster.strength} isGrey = {true}/>
            <StatBox name = {"Dxt"} value = {monster.dexterity} isGrey = {false}/>
            <StatBox name = {"BB"} value = {monster.bodybuild} isGrey = {true}/>
            <StatBox name = {"Int"} value = {monster.intelligence} isGrey = {false}/>
            <StatBox name = {"Wis"} value = {monster.wisdom} isGrey = {true}/>
            <StatBox name = {"Chr"} value = {monster.charisma} isGrey = {false}/>
            {safe_bonus.length !== 0 &&   <p dangerouslySetInnerHTML={{ __html: `<b>Saving Throws</b> ${safe_bonus}` }} />}
            {dmgV.length !== 0 &&   <p><b>Vulnerabilities</b> {dmgV} </p>}
            {dmgR.length !== 0 &&   <p><b>Resistances</b> {dmgR} </p>}
            {dmgI.length !== 0 &&   <p><b>Immunities</b> {dmgI} </p>}
            {isNaN(habitats) && <p><b>Habitats </b>{habitats}</p>}
            <p><b>Danger</b> {monster.danger.degree} ({monster.danger.expGain})</p>
            <p><b>Skill bonus +{monster.skill_bonus}</b></p>
            {isNaN(monster.features) && <p className = {style.rows}><b>Features</b></p>}
            <p>{monster.features}</p>
            {isNaN(actions) && <p className = {style.rows}><b>Actions</b></p>}
            <div>
            {actions.map((action, index) => (
                <p key={`action-${index}`} dangerouslySetInnerHTML={{ __html: `<b>${action.name}:</b> ${action.info}` }} />
            ))}
            {legActions.length !== 0 && <p className={style.rows}><b>Legendary Actions</b></p>}
            {legActions.map((action, index) => (
                <p key={`legendary-${index}`} dangerouslySetInnerHTML={{ __html: `<b>${action.name}:</b> ${action.info}` }} />
            ))}
            </div>
            {isNaN(monster.description) && <p className = {style.rows}><b>Description</b></p>}
            <p>{monster.description}</p>
        </div>
    )
}

export default Monster;