import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import style from './calculator.module.css';
import Mstyle from '../mainStyle.module.css';
import CustomDropdown from '../CustomDropdown.jsx';
import {useEffect, useState} from 'react';

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

  const [playerCount, setPlayerCount] = useState('');
  const [playerLevel, setPlayerLevel] = useState('');

  const [monsterDL, setMonsterDL] = useState('');
  const [monsterCount, setMonsterCount] = useState('');

  const [selectedClass, setSelectedClass] = useState('');
  const [characterLevel, setCharacterLevel] = useState('');

  const [selectedTopo, setSelectedTopo] = useState('');

  const [monsterList, setMonsterList] = useState('');
  const [charactersList, setCharactersList] = useState('');

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
                .then(data => setMonsterList(data.monsters))
                .catch(err => console.error('Failed to load items:', err));
    }, [location.search]);
  }

  const resetAll = () => {
    setPlayers([]);
    setMonsters([]);
    setPlayerCount('');
    setPlayerLevel('');
    setMonsterDL('');
    setMonsterCount('');
    setSelectedClass('');
    setCharacterLevel('');
    setSelectedTopo('');
  };

  const addPlayer = () => {
    const count = parseInt(playerCount);
    const level = parseInt(playerLevel);
    if (!isNaN(count) && !isNaN(level) && count > 0 && level > 0 && level < 21) {
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
    if (isValidCR && count > 0) {
      setMonsters([...monsters, { cr: monsterDL, count }]);
      setMonsterDL('');
      setMonsterCount('');
    }
  };

  const removePlayer = (index) => {
    const newList = [...players];
    newList.splice(index, 1);
    setPlayers(newList);
  };

  const removeMonster = (index) => {
    const newList = [...monsters];
    newList.splice(index, 1);
    setMonsters(newList);
  };

  const addCharacter = () => {
    const level = parseInt(characterLevel);
    if (selectedClass && !isNaN(level) && level > 0 && level < 21) {
      setPlayers([...players, { className: selectedClass, level: level }]);
      setSelectedClass('');
      setCharacterLevel('');
    }
  };

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
                  {m.count} {m.count !== 1 ? "Monsters" : "Monster"} of {m.cr} danger level
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

        <p id="diff" className={style.diff}></p>
        <button onClick={submit} className={style.specButton} id={style.calcButton}>Calculate</button>
        <button onClick={resetAll} className={style.specButton} id={style.resetButton}>Reset</button>
      </div>
    </>
  );
}

export default CombatCalculator;
