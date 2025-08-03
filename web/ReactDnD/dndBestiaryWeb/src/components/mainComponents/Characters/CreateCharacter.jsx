import { useNavigate } from "react-router-dom";
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

const minStat = 8;
const maxStat = 15;

const getBonus = (value) => {
    if(value < 10){
        return -1;
    }else if(value < 12){
        return 0;
    }else if(value < 14){
        return 1;
    }else if(value < 16){
        return 2;
    }else if(value < 18){
        return 3;
    }else if(value < 20){
        return 4;
    }else if(value < 22){
        return 5;
    }
}

function CreateCharacter() {
    const navigate = useNavigate();
    const [characters, setCharacters] = useState(null);

    const raceBonuses = {
        "Mountain Dwarf" : [2, 0, 2, 0, 0, 0],
        "Hill Dwarf" : [0, 0, 2, 0, 1, 0],
        "High Elf" : [0, 2, 0, 1, 0, 0],
        "Wood Elf" : [0, 2, 0, 0, 1, 0],
        "Drow (Dark Elf)" : [0, 2, 0, 0, 0, 1],
        "Lightfoot Halfling" : [0, 2, 0, 0, 0, 1],
        "Stout Halfling" : [0, 2, 1, 0, 0, 0],
        "Human" : [1, 1, 1, 1, 1, 1],
        "Dragonborn" : [2, 0, 0, 0, 0, 1],
        "Forest Gnome" : [0, 1, 0, 2, 0, 0],
        "Rock Gnome" : [0, 0, 1, 0, 2, 0],
        "Half-Elf" : [0, 1, 0, 0, 1, 2],
        "Half-Orc" : [2, 1, 0, 0, 0, 0],
        "Tiefling" : [0, 0, 0, 1, 0, 2]
    };

    let statList = [8, 8, 8, 8, 8, 8];
    let raceBonusList = [0, 0, 0, 0, 0, 0];
    let pointsLeft = 27;

    const statUp = (statNumber) =>{
        let statStrName = "";
        switch (statNumber){
            case 0:
                statStrName = "Strength";
                break;
            case 1:
                statStrName = "Dexterity";
                break;
            case 2:
                statStrName = "Constitution";
                break;
            case 3:
                statStrName = "Intelligence";
                break;
            case 4:
                statStrName = "Wisdom";
                break;
            case 5:
                statStrName = "Charisma";
                break;
        }
        if(statList[statNumber]==13 || statList[statNumber]==14){
            if(pointsLeft<2){
                return;
            }
            statList[statNumber]++;
            pointsLeft-=2;
            if(statList[statNumber]==15){
                document.getElementById(statStrName+"UP").style.visibility = "hidden";
            }
            document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        }else if(pointsLeft>0){
            statList[statNumber]++;
            pointsLeft-=1;
            if(statList[statNumber]==9){
                document.getElementById(statStrName+"DOWN").style.visibility = "visible";
            }
            document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        }
        document.getElementById("statLeft").textContent = "Stat points left: " +    pointsLeft;
        updateFinalStat();
    };

    const statDown = (statNumber) =>{
        let statStrName = "";
        switch (statNumber){
            case 0:
                statStrName = "Strength";
                break;
            case 1:
                statStrName = "Dexterity";
                break;
            case 2:
                statStrName = "Constitution";
                break;
            case 3:
                statStrName = "Intelligence";
                break;
            case 4:
                statStrName = "Wisdom";
                break;
            case 5:
                statStrName = "Charisma";
                break;
        }
        if(statList[statNumber]==15 || statList[statNumber]==14){
            statList[statNumber]--;
            pointsLeft+=2;
            if(statList[statNumber]==14){
                document.getElementById(statStrName+"UP").style.visibility = "visible";
            }
            document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        }else{
            statList[statNumber]--;
            pointsLeft+=1;
            if(statList[statNumber]==8){
                document.getElementById(statStrName+"DOWN").style.visibility = "hidden";
            }
            document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        }
        document.getElementById("statLeft").textContent = "Stat points left: " + pointsLeft;
        updateFinalStat();
    };

    const updateRaceB = (raceV) => {
        raceBonusList = raceBonuses[raceV];
        for(let i=0; i<6; i++){
            document.getElementById(i+"RaceBonus").textContent = "+" + raceBonusList[i];
            if(raceBonusList[i]>0){
                document.getElementById(i+"RaceBonus").style.visibility = "visible";
            }else{
                document.getElementById(i+"RaceBonus").style.visibility = "hidden";
            }
        }
        updateFinalStat();
    }

    const updateFinalStat = () => {
        for(let i=0; i<6; i++){
            let statVal = raceBonusList[i]+statList[i];
            document.getElementById(i+"FinalVal").textContent = statVal + " ("+getBonus(statVal)+")";
        }
    }

    return<div>
            <form>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" minlength="2" maxlength="32" required/>

                <label for="level">Level</label>
                <input type="number" id="level" name="level" min="1" max="20" required/>

                <label for="class">Class</label>
                <select id="class" name="class" required>
                    <option value="">--Select Class--</option>
                    <option>Barbarian</option>
                    <option>Bard</option>
                    <option>Cleric</option>
                    <option>Druid</option>
                    <option>Fighter</option>
                    <option>Monk</option>
                    <option>Paladin</option>
                    <option>Ranger</option>
                    <option>Rogue</option>
                    <option>Sorcerer</option>
                    <option>Warlock</option>
                    <option>Wizard</option>
                </select>

                <label for="race">Race</label>
                <select id="race" name="race" onClick={(e) => updateRaceB(e.target.value)} required>
                    <option value="">--Select Race--</option>
                    <option>Human</option>
                    <option>High Elf</option>
                    <option>Wood Elf</option>
                    <option>Drow (Dark Elf)</option>
                    <option>Hill Dwarf</option>
                    <option>Mountain Dwarf</option>
                    <option>Lightfoot Halfling</option>
                    <option>Stout Halfling</option>
                    <option>Forest Gnome</option>
                    <option>Rock Gnome</option>
                    <option>Dragonborn</option>
                    <option>Half-Elf</option>
                    <option>Half-Orc</option>
                    <option>Tiefling</option>
                </select>
                
                <p id="statLeft">Stat points left: {pointsLeft}</p>

                <div className={style.statBoxMain}>
                    <div className={style.statBox}>
                        <p className={style.statName} style={{ visibility: "hidden" }}>1</p>
                        <button className={style.statUpB} style={{ visibility: "hidden" }}>1</button>
                        <p className={style.statVal}  style={{ visibility: "hidden" }}>1</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }}>1</button>
                        <p>Race bonus:</p>
                        <p>Final value:</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Strength</p>
                        <button className={style.statUpB} id="StrengthUP" onClick={() => statUp(0)}>+</button>
                        <p className={style.statVal} id="StrengthVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="StrengthDOWN" onClick={() => statDown(0)}>-</button>
                        <p id="0RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="0FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Dexterity</p>
                        <button className={style.statUpB} id="DexterityUP" onClick={() => statUp(1)}>+</button>
                        <p className={style.statVal} id="DexterityVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="DexterityDOWN" onClick={() => statDown(1)}>-</button>
                        <p id="1RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="1FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Constitution</p>
                        <button className={style.statUpB} id="ConstitutionUP" onClick={() => statUp(2)}>+</button>
                        <p className={style.statVal} id="ConstitutionVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="ConstitutionDOWN" onClick={() => statDown(2)}>-</button>
                        <p id="2RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="2FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Intelligence</p>
                        <button className={style.statUpB} id="IntelligenceUP" onClick={() => statUp(3)}>+</button>
                        <p className={style.statVal} id="IntelligenceVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="IntelligenceDOWN" onClick={() => statDown(3)}>-</button>
                        <p id="3RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="3FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Wisdom</p>
                        <button className={style.statUpB} id="WisdomUP" onClick={() => statUp(4)}>+</button>
                        <p className={style.statVal} id="WisdomVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="WisdomDOWN" onClick={() => statDown(4)}>-</button>
                        <p id="4RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="4FinalVal">8 (-1)</p>
                    </div>
                    
                    <div className={style.statBox}>
                        <p className={style.statName}>Charisma</p>
                        <button className={style.statUpB} id="CharismaUP" onClick={() => statUp(5)}>+</button>
                        <p className={style.statVal} id="CharismaVAL">8</p>
                        <button className={style.statDownB} style={{ visibility: "hidden" }} id="CharismaDOWN" onClick={() => statDown(5)}>-</button>
                        <p id="5RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p id="5FinalVal">8 (-1)</p>
                    </div>
                </div>
            </form>
        </div>;
}

export default CreateCharacter;