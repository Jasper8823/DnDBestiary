import { useNavigate } from "react-router-dom";
import style from './calculator.module.css';
import Mstyle from '../mainStyle.module.css';
import { useState } from 'react';

function CombatCalculator() {
  const navigate = useNavigate();

  const [players, setPlayers] = useState([]);
  const [monsters, setMonsters] = useState([]);

  const [playerCount, setPlayerCount] = useState('');
  const [playerLevel, setPlayerLevel] = useState('');

  const [monsterDL, setMonsterDL] = useState('');
  const [monsterCount, setMonsterCount] = useState('');

     const resetAll = () => {
        setPlayers([]);
        setMonsters([]);
        setPlayerCount('');
        setPlayerLevel('');
        setMonsterDL('');
        setMonsterCount('');
    };

    const addPlayer = () => {
        const count = parseInt(playerCount);
        const level = parseInt(playerLevel);
        if (!isNaN(count) && !isNaN(level) && count > 0 && level > 0 && level < 21) {
            const newPlayers = [...players];
            newPlayers.push({ count, level });
            setPlayers(newPlayers);
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
            const newMonsters = [...monsters, { cr: monsterDL, count }];
            setMonsters(newMonsters);
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

    const submit = async () => {
        if (players.length > 0 && monsters.length > 0) {
            try {
                const response = await fetch('http://localhost:8080/calculate', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ players, monsters }),
                });

                if (!response.ok) {
                    throw new Error(`Server error: ${response.status}`);
                }

                const data = await response.json();
                if(data){
                    document.getElementById("diff").textContent="Figth complexity: "+data["difficulty"];
                }

            } catch (error) {
                console.error('Error submitting data:', error);
            }
        } else {
            console.log('Players or monsters are missing');
            console.log(players);
            console.log(monsters);
        }
    };

    return (
        <div className={style.calculatorBox}>
            <div className={style.box} id={style.playerBox}>
                <p><b>Player Characters</b></p>
                <div className={style.inputBox}>
                    <input type="number" placeholder="Number" value={playerCount}
                        onChange={(e) => setPlayerCount(e.target.value)} />
                    <input type="number" placeholder="Level" value={playerLevel}
                        onChange={(e) => setPlayerLevel(e.target.value)} />
                    <button class={style.addButton} onClick={addPlayer}>Add</button>
                </div>
                <ul>
                {players.map((p, i) => (
                    <li key={i}>
                    {p.count} charactes with {p.level} level
                    <button  className={style.removeButton} onClick={() => removePlayer(i)}>❌</button>
                    </li>
                ))}
                </ul>
            </div>

            <div className={style.box} id={style.monsterBox}>
                <p><b>Enemies</b></p>
                <div className={style.inputBox}>
                    <input type="number" placeholder="Number" value={monsterCount}
                        onChange={(e) => setMonsterCount(e.target.value)} />
                    <input type="text" placeholder="Danger level" value={monsterDL}
                        onChange={(e) => setMonsterDL(e.target.value)} />
                    <button class={style.addButton} onClick={addMonster}>Add</button>
                </div>
                <ul>
                {monsters.map((m, i) => (
                    <li key={i}>
                    {m.count} {m.count != 1 ? "Monsters" : "Monster" } of {m.cr} danger level
                    <button className={style.removeButton} onClick={() => removeMonster(i)}>❌</button>
                    </li>
                ))}
                </ul>
            </div>

            <button onClick={resetAll} className={style.specButton} id={style.resetButton}>Reset</button>
            
            <button onClick={submit} className={style.specButton} id={style.calcButton}>Calculate</button>
            <p id="diff" className={style.diff}></p>
        </div>
    );
}

export default CombatCalculator;