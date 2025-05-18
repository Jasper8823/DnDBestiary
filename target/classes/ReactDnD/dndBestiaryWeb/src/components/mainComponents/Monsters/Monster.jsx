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

    if (!monster) return <p>Loading monsters...</p>;
    return(
        <div className={style.mainBox}>
            <p id={style.monsterName}><b>{monster.name}</b></p>
            <div className={style.sizeTypeWVBox}>
                <p><i>{monster.size}</i></p>
                <p>{monster.type}</p>
                <p>{monster.worldView}</p>
            </div>
            <p><b>Armor type</b> 11</p>
            <p><b>Hit points</b> 26 (4d8+8)</p>
            <p><b>Speed</b> 20</p>
            <StatBox name = {"Str"} value = {10} isGrey = {true}/>
            <StatBox name = {"Str"} value = {22} isGrey = {false}/>
            <StatBox name = {"Str"} value = {9} isGrey = {true}/>
            <StatBox name = {"Str"} value = {10} isGrey = {false}/>
            <StatBox name = {"Str"} value = {22} isGrey = {true}/>
            <StatBox name = {"Str"} value = {9} isGrey = {false}/>
            <p><b>Danger</b> {monster.danger} (450)</p>
            <p className = {style.features}><b>Features</b></p>
        </div>
    )
}

export default Monster;