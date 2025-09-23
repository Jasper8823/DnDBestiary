import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";

const maxStat = 20;

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
    }else if(value < 24){
        return 6;
    }
}

function CreateCharacterSpells() {
    const { uuid } = useParams();
    const navigate = useNavigate();
    
    const [pointsLeft, setPointsLeft] = useState(0);

    const [neededInfo, setNeededInfo] = useState(null);
    const [selectedSpells, setSelectedSpells] = useState([]);

    const [statList, setStatList] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/create-character?uuid=${uuid}`)
            .then(response => response.json())
            .then(data => {
                if(data.stat_raise===-1){
                    navigate(`/characters`);
                }
                setNeededInfo(data);
                setPointsLeft(data.stat_raise);
                const adjustedStats = data.stats.map(
                    (val, i) => val - raceBonuses[data.race][i]
                );
                setStatList(adjustedStats);
                setSelectedSpells(Array(data.spells_num).fill(""));
            })
            .catch(error => console.error("Error fetching data:", error));
    }, [uuid]);
    

    if (!neededInfo) {
        return <div><p>Loading</p></div>;
    }

    console.log(statList);

    const spellsList = Object.keys(neededInfo.spells);

    const handleSelectChange = (index, value) => {
        setSelectedSpells(prev => {
            const newSelection = [...prev];
            newSelection[index] = value;
            return newSelection;
        });
    };

    const getAvailableSpells = (index) => {
        let chosenSpells = Array.from(selectedSpells);

        chosenSpells[index] = "";

        return spellsList.filter(spell => !chosenSpells.includes(spell));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            uuid :  uuid,
            spells: selectedSpells,
            stats: getFinalStats()
        };

        try {
            console.log(data);
            const response = await fetch("http://localhost:8080/create-character-spells", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
            navigate(`/characters`);
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
        if(pointsLeft>0){
            statList[statNumber]++;
            setPointsLeft(pointsLeft => pointsLeft-1);
            if(statList[statNumber]==20){
                document.getElementById(statStrName+"UP").style.visibility = "hidden";
            }else if(statList[statNumber]==neededInfo.stats[statNumber]+1-raceBonuses[neededInfo.race][statNumber]){
                document.getElementById(statStrName+"DOWN").style.visibility = "visible";
            }
            document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        }
        let newPointsLeft = pointsLeft - 1;
        if(newPointsLeft<0){
            newPointsLeft=0;
        }
        document.getElementById("statLeft").textContent = "Stat points left: " + newPointsLeft;
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
        statList[statNumber]--;
        setPointsLeft(pointsLeft => pointsLeft+1);
            
        if(statList[statNumber]==19){
            document.getElementById(statStrName+"UP").style.visibility = "visible";
        }
        if(statList[statNumber]==neededInfo.stats[statNumber]-raceBonuses[neededInfo.race][statNumber]){
            document.getElementById(statStrName+"DOWN").style.visibility = "hidden";
        }
        document.getElementById(statStrName+"VAL").textContent = statList[statNumber];
        let newPointsLeft = pointsLeft + 1;
        document.getElementById("statLeft").textContent = "Stat points left: " + newPointsLeft;
        updateFinalStat();
    };

    const updateFinalStat = () => {
        for(let i=0; i<6; i++){
            let statVal = raceBonuses[neededInfo.race][i]+statList[i];
            document.getElementById(i+"FinalVal").textContent = statVal + " ("+getBonus(statVal)+")";
        }
    }

    const getFinalStats = () => {
        let fstat = []
        for(let i=0; i<6; i++){
            fstat[i] = raceBonuses[neededInfo.race][i]+statList[i];
        }
        return fstat;
    }

    return (
        <div>
            <form className={style.characterForm} onSubmit={handleSubmit}>
                {selectedSpells.map((selected, index) => (
                    <div key={index}>
                        <select
                            required
                            value={selected}
                            onChange={(e) => handleSelectChange(index, e.target.value)}
                        >
                            <option value="">-- Select a spell --</option>
                            {getAvailableSpells(index).map(spell => (
                                <option key={spell} value={spell}>
                                    {neededInfo.spells[spell]+" "+spell}
                                </option>
                            ))}
                        </select>
                    </div>
                ))}
                {neededInfo.stat_raise!=0 && 
                                <div>
                                <p id="statLeft">Stat points left: {neededInfo.stat_raise}</p>
                                <div className={style.statBoxMain}>
                                    <div className={style.statBox}>
                                        <p className={style.statName} style={{ visibility: "hidden" }}>1</p>
                                        <button type="button" className={style.statUpB} style={{ visibility: "hidden" }}>1</button>
                                        <p className={style.statVal}  style={{ visibility: "hidden" }}>1</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }}>1</button>
                                        <p>Race bonus:</p>
                                        <p>Final value:</p>
                                    </div>
                
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Strength</p>
                                        <button type="button" className={style.statUpB} id="StrengthUP" onClick={() => statUp(0)}>+</button>
                                        <p className={style.statVal} id="StrengthVAL">{neededInfo.stats[0]-raceBonuses[neededInfo.race][0]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="StrengthDOWN" onClick={() => statDown(0)}>-</button>
                                        <p id="0RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][0] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][0]}</p>
                                        <p id="0FinalVal">{neededInfo.stats[0]} ({Math.floor((neededInfo.stats[0]-10)/2)})</p>
                                    </div>
                
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Dexterity</p>
                                        <button type="button" className={style.statUpB} id="DexterityUP" onClick={() => statUp(1)}>+</button>
                                        <p className={style.statVal} id="DexterityVAL">{neededInfo.stats[1]-raceBonuses[neededInfo.race][1]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="DexterityDOWN" onClick={() => statDown(1)}>-</button>
                                        <p id="1RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][1] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][1]}</p>
                                        <p id="1FinalVal">{neededInfo.stats[1]} ({Math.floor((neededInfo.stats[1]-10)/2)})</p>
                                    </div>
                
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Constitution</p>
                                        <button type="button" className={style.statUpB} id="ConstitutionUP" onClick={() => statUp(2)}>+</button>
                                        <p className={style.statVal} id="ConstitutionVAL">{neededInfo.stats[2]-raceBonuses[neededInfo.race][2]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="ConstitutionDOWN" onClick={() => statDown(2)}>-</button>
                                        <p id="2RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][2] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][2]}</p>
                                        <p id="2FinalVal">{neededInfo.stats[2]} ({Math.floor((neededInfo.stats[2]-10)/2)})</p>
                                    </div>
                
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Intelligence</p>
                                        <button type="button" className={style.statUpB} id="IntelligenceUP" onClick={() => statUp(3)}>+</button>
                                        <p className={style.statVal} id="IntelligenceVAL">{neededInfo.stats[3]-raceBonuses[neededInfo.race][3]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="IntelligenceDOWN" onClick={() => statDown(3)}>-</button>
                                        <p id="3RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][3] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][3]}</p>
                                        <p id="3FinalVal">{neededInfo.stats[3]} ({Math.floor((neededInfo.stats[3]-10)/2)})</p>
                                    </div>
                
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Wisdom</p>
                                        <button type="button" className={style.statUpB} id="WisdomUP" onClick={() => statUp(4)}>+</button>
                                        <p className={style.statVal} id="WisdomVAL">{neededInfo.stats[4]-raceBonuses[neededInfo.race][4]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="WisdomDOWN" onClick={() => statDown(4)}>-</button>
                                        <p id="4RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][4] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][4]}</p>
                                        <p id="4FinalVal">{neededInfo.stats[4]} ({Math.floor((neededInfo.stats[4]-10)/2)})</p>
                                    </div>
                                    
                                    <div className={style.statBox}>
                                        <p className={style.statName}>Charisma</p>
                                        <button type="button" className={style.statUpB} id="CharismaUP" onClick={() => statUp(5)}>+</button>
                                        <p className={style.statVal} id="CharismaVAL">{neededInfo.stats[5]-raceBonuses[neededInfo.race][5]}</p>
                                        <button type="button" className={style.statDownB} style={{ visibility: "hidden" }} id="CharismaDOWN" onClick={() => statDown(5)}>-</button>
                                        <p id="5RaceBonus" style={{ visibility: raceBonuses[neededInfo.race][5] == 0 ? "hidden" : "visible" }}>+{raceBonuses[neededInfo.race][5]}</p>
                                        <p id="5FinalVal">{neededInfo.stats[5]} ({Math.floor((neededInfo.stats[5]-10)/2)})</p>
                                    </div>
                                </div>
                                </div>
                            }
                <button className={style.submit}  type="submit">Finish</button>
            </form>
        </div>
    );
}

export default CreateCharacterSpells;