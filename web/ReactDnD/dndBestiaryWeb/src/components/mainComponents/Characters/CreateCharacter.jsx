import { useParams } from 'react-router-dom';
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";

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

const raceBonuses = {
    "Mountain Dwarf" : [2, 0, 2, 0, 0, 0],
    "Hill Dwarf" : [0, 0, 2, 0, 1, 0],
    "High Elf" : [0, 2, 0, 1, 0, 0],
    "Wood Elf" : [0, 2, 0, 0, 1, 0],
    "Dark Elf" : [0, 2, 0, 0, 0, 1],
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

const archetypes = {
    "Barbarian" : ["Path of the berserker","Path of the totem warrior"],
    
    "Bard" : ["College of valor","College of knowledge"],
    
    "Cleric" : ["Life domain","Knowledge domain"
        ,"Trickery domain","Nature domain","Light domain"],
    
        "Druid" : ["Circle of the land","Circle of the moon"],
    
    "Fighter" : ["Master of martial arts","Mystic Knight","Champion"],
    
    "Monk" : ["Way of the open hand","Way of the four elements","Way of shadow"],
    
    "Paladin" : ["Oath of devotion","Oath of vengeance","Oath of the ancients"],
    
    "Ranger" : ["Hunter","Beast master"],

    "Rogue" : ["Thief","Assassin","Arcane trickster"],

    "Sorcerer" : ["Dragon bloodline","Wild magic"],

    "Warlock" : ["The archfey","The fiend","The great old one"],

    "Wizard" : ["School of illusion"
        ,"School of necromancy","School of abjuration"
        ,"School of enchantment","School of transmutation","School of divination"],

}

function CreateCharacter() {
    const { userid } = useParams();
    const navigate = useNavigate();
    const [characters, setCharacters] = useState(null);

    if(userid){
        const updateUserId = (async () =>{

            try {
                const response = await fetch(`http://localhost:8080/prolong?userid=${userid}`, {
                    method: "POST",
                });
                const rawText = await response.text();
                if(rawText == "1"){
                    navigate(`/`);
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Error while sending request.");
            }
        })

        updateUserId();
    }

    let statList = [8, 8, 8, 8, 8, 8];
    let raceBonusList = [0, 0, 0, 0, 0, 0];
    let pointsLeft = 27;

    const handleSubmit = async (event) => {
        event.preventDefault();

        const form = event.target;

        const data = {
            name: form.name.value,
            level: form.level.value,
            class: form.class.value,
            race: form.race.value,
            backstory: form.backstory.value,
            archetype: form.archetype.value,
            stats: getFinalStats(),
            sessionid: userid
        };

        try {
            const response = await fetch("http://localhost:8080/create-character", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
            const rawText = await response.text();
            if(rawText){
                navigate(`/${userid}/create-character/${rawText}`);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error while sending request.");
        }
    };

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

    const getFinalStats = () => {
        let fstat = []
        for(let i=0; i<6; i++){
            fstat[i] = raceBonusList[i]+statList[i];
        }
        return fstat;
    }

    const updateArchetype = (className) => {
        let archetypesList = archetypes[className];
        const selectE =  document.getElementById("archetype");
        selectE.selectedIndex = -1;
        selectE.innerHTML = "";
        archetypesList.forEach(arName => {
            var option = document.createElement("option");
            option.text = arName;
            selectE.appendChild(option);
        });
    }

    return<div>
            <form className={style.characterForm} onSubmit={handleSubmit}>
                <label className={style.createLable} htmlFor="name">Name</label>
                <input type="text" id="name" name="name" minlength="2" maxlength="32" required/>

                <label className={style.createLabel} htmlFor="level">Level</label>
                <input type="number" id="level" name="level" min="1" max="20" required/>

                <label className={style.createLabel} for="class">Class</label>
                <select id="class" name="class" onChange={(e) => updateArchetype(e.target.value)} required>
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

                <label className={style.createLabel} for="race">Race</label>
                <select id="race" name="race" onChange={(e) => updateRaceB(e.target.value)} required>
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

                <label className={style.createLabel} for="backstory">Backstory</label>
                <select id="backstory" name="backstory" required>
                    <option value="">--Select Backstory--</option>
                    <option>Acolyte</option>
                    <option>Criminal</option>
                    <option>Folk Hero</option>
                    <option>Noble</option>
                    <option>Sage</option>
                    <option>Soldier</option>
                    <option>Urchin</option>
                    <option>Entertainer</option>
                    <option>Haunted One</option>
                    <option>Guild Artisan</option>
                    <option>Faceless One</option>
                    <option>Hermit</option>
                    <option>Bounty Hunter</option>
                    <option>Feylost</option>
                    <option>Recluse</option>
                    <option>Far Traveler</option>
                </select>

                <label className={style.createLabel} for="archetype">Archetype</label>
                <select id="archetype" name="archetype" required>
                    <option value="">--Select Archetype--</option>
                </select>

                <div className={style.statBoxMain}>
                    <div className={style.statBoxM}>
                        <p className={style.pointsLeft}  id="statLeft">Stat points left: {pointsLeft}</p>
                        <p className={style.statVal}  style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statVal}  style={{ visibility: "hidden" }}>1</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }}>1</button>
                        <p className={style.statText}>Race bonus:</p>
                        <p className={style.statText}>Final value:</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Strength</p>
                        <button type="button" className={style.statUpB} id="StrengthUP" onClick={() => statUp(0)}>+</button>
                        <p className={style.statVal} id="StrengthVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="StrengthDOWN" onClick={() => statDown(0)}>-</button>
                        <p className={style.statText} id="0RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="0FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Dexterity</p>
                        <button type="button" className={style.statUpB} id="DexterityUP" onClick={() => statUp(1)}>+</button>
                        <p className={style.statVal} id="DexterityVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="DexterityDOWN" onClick={() => statDown(1)}>-</button>
                        <p className={style.statText} id="1RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="1FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Constitution</p>
                        <button type="button" className={style.statUpB} id="ConstitutionUP" onClick={() => statUp(2)}>+</button>
                        <p className={style.statVal} id="ConstitutionVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="ConstitutionDOWN" onClick={() => statDown(2)}>-</button>
                        <p className={style.statText} id="2RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="2FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Intelligence</p>
                        <button type="button" className={style.statUpB} id="IntelligenceUP" onClick={() => statUp(3)}>+</button>
                        <p className={style.statVal} id="IntelligenceVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="IntelligenceDOWN" onClick={() => statDown(3)}>-</button>
                        <p className={style.statText} id="3RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="3FinalVal">8 (-1)</p>
                    </div>

                    <div className={style.statBox}>
                        <p className={style.statName}>Wisdom</p>
                        <button type="button" className={style.statUpB} id="WisdomUP" onClick={() => statUp(4)}>+</button>
                        <p className={style.statVal} id="WisdomVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="WisdomDOWN" onClick={() => statDown(4)}>-</button>
                        <p className={style.statText} id="4RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="4FinalVal">8 (-1)</p>
                    </div>
                    
                    <div className={style.statBox}>
                        <p className={style.statName}>Charisma</p>
                        <button type="button" className={style.statUpB} id="CharismaUP" onClick={() => statUp(5)}>+</button>
                        <p className={style.statVal} id="CharismaVAL">8</p>
                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="CharismaDOWN" onClick={() => statDown(5)}>-</button>
                        <p className={style.statText} id="5RaceBonus" style={{ visibility: "hidden" }}>1</p>
                        <p className={style.statText} id="5FinalVal">8 (-1)</p>
                    </div>
                </div>
                <button className={style.submit} id="submit">Create</button>
            </form>
        </div>;
}

export default CreateCharacter;