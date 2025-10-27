import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import style from './calculator.module.css';
import Mstyle from '../mainStyle.module.css';
import CustomDropdown from '../CustomDropdown.jsx';
import {useEffect, useState} from 'react';
import SingleDropdown from '../SingleDropdown.jsx';

const classes = [
  "Barbarian","Bard","Cleric","Druid",
  "Fighter","Monk","Paladin","Ranger",
  "Rogue","Sorcerer","Warlock","Wizard"
];

const topography = {
  open_space: "Open space",
  semi_opened_space: "Semi-opened space",
  closed_space: "Closed space",
  low_permeability: "Low permeability",
  middle_permeability: "Middle permeability",
  high_permeability: "High permeability",
  low_altitude: "Low altitude",
  middle_altitude: "Middle altitude",
  high_altitude: "High altitude",
  low_visibility: "Low visibility",
  middle_visibility: "Middle visibility",
  high_visibility: "High visibility"
};

function CombatCalculator() {
  const { userid } = useParams();
  const navigate = useNavigate();

  if (userid) {
    const updateUserId = (async () => {
      try {
        const response = await fetch(`http://localhost:8080/prolong?userid=${userid}`, {
          method: "POST",
        });
        const rawText = await response.text();
        if (rawText == "1") {
          navigate(`/`);
        }
      } catch (error) {
        console.error("Error:", error);
        alert("Error while sending request.");
      }
    });

    updateUserId();
  }

  const [players, setPlayers] = useState([]);
  const [monsters, setMonsters] = useState([]);

  const [playerCount, setPlayerCount] = useState("");
  const [playerLevel, setPlayerLevel] = useState("");

  const [monsterDL, setMonsterDL] = useState('');

  const [selectedClass, setSelectedClass] = useState("");
  const [characterLevel, setCharacterLevel] = useState("");
  const [selectedTopo, setSelectedTopo] = useState("");

  const [monsterList, setMonsterList] = useState([]);
  const [charactersList, setCharactersList] = useState([]);

  const [selectedMonster, setSelectedMonster] = useState("");
  const [monsterCountI, setMonsterCountI] = useState("");
  const [monsterCount, setMonsterCount] = useState("");
  const [selectedCharacter, setSelectedCharacter] = useState("");

  const addCharacter = () => {
    const level = parseInt(characterLevel);
    if (selectedClass && !isNaN(level) && level > 0 && level < 21) {
      setPlayers([...players, { className: selectedClass, level: level }]);
      setSelectedClass('');
      setCharacterLevel('');
    }
  };

  if(userid){
    useEffect(() => {
                const query = location.search;
                fetch(`http://localhost:8080/getCombatCalculator?userid=${userid}`, {
                method: 'GET',
                })
                .then(res => res.json())
                .then(data => {
                  setMonsterList(data.monsters);
                  setCharactersList(data.characters);
                })
                .catch(err => console.error('Failed to load items:', err));
    }, [location.search]);
  }else{
    useEffect(() => {
                const query = location.search;
                fetch(`http://localhost:8080/getCombatCalculator`, {
                method: 'GET',
                })
                .then(res => res.json())
                .then(data => {
                  setMonsterList(data.monsters);
                })
                .catch(err => console.error('Failed to load items:', err));
    }, [location.search]);
  }

  const resetAll = () => {
    setPlayers([]);
    setMonsters([]);
    setPlayerCount("");
    setPlayerLevel("");
    setSelectedClass("");
    setCharacterLevel("");
    setSelectedTopo("");
    setSelectedMonster("");
    setMonsterCount("");
    setSelectedCharacter("");
  };

  const handleCharacterSelect = (name, value) => {
    if (!value) return;
    const char = charactersList.find(c => c.id == value);
    if (!char) return;
    if (players.some(p => p.id === char.id)) return;
    setPlayers([...players, { id: char.id, name: char.name, level: char.level }]);
    setSelectedCharacter("");
  };

  const handleMonsterSelect = (name, value) => {
    setSelectedMonster(value);
  };

  const handleMonsterAddI = () => {
    if (!selectedMonster || !monsterCountI) return;
    const count = parseInt(monsterCountI);
    if (isNaN(count) || count < 1 || count > 5) return;
    const mon = monsterList.find(m => m.id == selectedMonster);
    if (!mon) return;
    setMonsters([...monsters, { id: mon.id, name: mon.name, danger: mon.danger, count }]);
    setSelectedMonster("");
    setMonsterCount("");
  }

  const addPlayer = () => {
    const count = parseInt(playerCount);
    const level = parseInt(playerLevel);
    if (!isNaN(count) && !isNaN(level) && count > 0 && count < 21 && level > 0 && level < 21) {
      setPlayers([...players, { count, level }]);
      setPlayerCount('');
      setPlayerLevel('');
    }
  };

  const addMonster = () => {
    const validMonsterCRs = [
      '1/8', '1/4', '1/2',
      ...Array.from({ length: 30 }, (_, i) => (i + 1).toString())
    ];
    const isValidCR = validMonsterCRs.includes(monsterDL);
    const count = parseInt(monsterCount);
    if (isValidCR && count > 0 && count < 21) {
      setMonsters([...monsters, { cr: monsterDL, count }]);
      setMonsterDL('');
      setMonsterCount('');
    }
  };

  const removePlayer = (index) => {
    const list = [...players];
    list.splice(index, 1);
    setPlayers(list);
  };

  const removeMonster = (index) => {
    const list = [...monsters];
    list.splice(index, 1);
    setMonsters(list);
  };

  let characterOptions;

  if(charactersList){
    characterOptions = Object.fromEntries(
      charactersList.map(c => [c.id, `${c.name} (Lv ${c.level})`])
    );
  }

  const monsterOptions = Object.fromEntries(
    monsterList.map(m => [m.id, `${m.name} (Danger ${m.danger > 100 ? `1/${m.danger-100}` : m.danger})`])
  );


  const submit = async () => {
    if (players.length > 0 && monsters.length > 0) {
      try {
          const response = await fetch('http://localhost:8080/calculate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              players,
              monsters,
              topography: selectedTopo
            }),
          });
        if (!response.ok) throw new Error(`Server error: ${response.status}`);
        const data = await response.json();
        if (data) {
          document.getElementById("diff").textContent =
            "Fight complexity: " + data["difficulty"];
        }
      } catch (error) {
        console.error('Error submitting data:', error);
      }
    } else {
      console.log('Players or monsters are missing');
    }
  };

  return (
    <>
      <div className={style.calculatorBox}>
        <div className={style.mainBox}>
          <div className={style.box} id={style.playerBox}>
            <p><b>Player Characters</b></p>
            <div className={style.inputBox}>
              <input
                type="number"
                placeholder="Number"
                value={playerCount}
                onChange={(e) => setPlayerCount(e.target.value)}
              />
              <input
                type="number"
                placeholder="Level"
                value={playerLevel}
                onChange={(e) => setPlayerLevel(e.target.value)}
              />
              <button className={style.addButton} onClick={addPlayer}>Add</button>
            </div>
            <ul>
              {players.map((p, i) => (
                <li key={i}>
                  {p.name && <>{p.name} (${p.level} level)</>}
                  {p.count && <>{p.count} characters with {p.level} level</>}
                  {p.className && <>{p.className} (level {p.level})</>}
                  <button
                    className={style.removeButton}
                    onClick={() => removePlayer(i)}
                  >
                    ❌
                  </button>
                </li>
              ))}
            </ul>
          </div>

          <div className={style.box} id={style.monsterBox}>
            <p><b>Enemies</b></p>
            <div className={style.inputBox}>
              <input
                type="number"
                placeholder="Number"
                value={monsterCount}
                onChange={(e) => setMonsterCount(e.target.value)}
              />
              <input
                type="text"
                placeholder="Danger level"
                value={monsterDL}
                onChange={(e) => setMonsterDL(e.target.value)}
              />
              <button className={style.addButton} onClick={addMonster}>Add</button>
            </div>
            <ul> 
              {monsters.map((m, i) => (
                <li key={i}>
                  {m.name ? <>{m.count} of {m.name}</> : <>{m.count} {m.count !== 1 ? "Monsters" : "Monster"} of {m.cr} danger level</>}
                  <button
                    className={style.removeButton}
                    onClick={() => removeMonster(i)}
                  >
                    ❌
                  </button>
                </li>
              ))}
            </ul>
          </div>
        </div>

        <div className={style.box} id={style.singleCharBox}>
          <p><b>Add character with class</b></p>
          <div className={style.inputBox}>
            <select value={selectedClass} onChange={(e) => setSelectedClass(e.target.value)}>
              <option value="">Select class</option>
              {classes.map((c, i) => (
                <option key={i} value={c}>{c}</option>
              ))}
            </select>
            <input
              type="number"
              placeholder="Level"
              value={characterLevel}
              onChange={(e) => setCharacterLevel(e.target.value)}
            />
            <button className={style.addButton} onClick={addCharacter}>Add</button>
          </div>
        </div>

        <div className={style.box} id={style.topographyBox}>
          <p><b>Battlefield Topography</b></p>
          <div className={style.inputBox}>
            <select
              value={selectedTopo}
              onChange={(e) => setSelectedTopo(e.target.value)}
            >
              <option value="">Select topography</option>
              {Object.entries(topography).map(([key, label]) => (
                <option key={key} value={key}>{label}</option>
              ))}
            </select>
          </div>
        </div>

        <div className={style.ImpMonsterBox}>
          <SingleDropdown name="monster" options={monsterOptions} selectedValue={selectedMonster} onChange={handleMonsterSelect} placeholder="Select monster" idName="monsterDropdown"/>
          <input type="number" id={style.numberOfImpMonster} name="level" min="1" max="5" onChange={(e) => setMonsterCountI(e.target.value)} required/>
          <button id={style.numberOfImpMonsterB} className={style.addButton} onClick={handleMonsterAddI}>Add</button>
        </div>

        {userid && <SingleDropdown name="character" options={characterOptions} selectedValue={selectedCharacter} onChange={handleCharacterSelect} placeholder="Select saved character" idName="characterDropdown"/>}

        <p id="diff" className={style.diff}></p>
        <button onClick={submit} className={style.specButton} id={style.calcButton}>Calculate</button>
        <button onClick={resetAll} className={style.specButton} id={style.resetButton}>Reset</button>
      </div>
    </>
  );
}

export default CombatCalculator;
