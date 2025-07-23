import { useNavigate } from "react-router-dom";
import style from './monsters.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function MonsterSmall() {
    const navigate = useNavigate();
    const [monsters, setMonsters] = useState(null);

    const handleClick = (monster) => {
        navigate(`/bestiary/${monster.id}`);
    };

    useEffect(() => {
        fetch('http://localhost:8080/getMonsters')
            .then(response => response.json())
            .then(data => setMonsters(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    if (!monsters) return <p>Loading monsters...</p>;

    let i=1;

    const listItems = monsters.map(monster => (
        <div key={monster.id} onClick={() => handleClick(monster)} className={Mstyle.bestiaryBox}>
            <p className={style.monsterLevel}>{monster.danger}</p>
            <p className={style.monsterName}>
                {monster.name.length > 20 ? monster.name.substring(0, 20) + "..." : monster.name}
            </p>
        </div>
    ));

    return <>{listItems}</>;
}

export default MonsterSmall;