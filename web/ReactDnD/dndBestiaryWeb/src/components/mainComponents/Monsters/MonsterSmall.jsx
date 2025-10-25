import { useParams } from 'react-router-dom';
import { useNavigate, useLocation } from 'react-router-dom';
import style from './monsters.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function MonsterSmall() {
    const { userid } = useParams();
    const navigate = useNavigate();
    const [monsters, setMonsters] = useState(null);

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

    const handleClick = (monster) => {
        navigate(`/bestiary/${monster.id}`);
    };

    useEffect(() => {
        const query = location.search;
        fetch(`http://localhost:8080/getMonsters${query}`, {
        method: 'GET',
        })
        .then(res => res.json())
        .then(data => setMonsters(data))
        .catch(err => console.error('Failed to load monsters:', err));
    }, [location.search]);


    if (!monsters) return <p>Loading monsters...</p>;

    let listItems = [];

    for (let i = 65; i <= 90; i++) { 
        let letter = String.fromCharCode(i);
        const monsterList = monsters[letter];
        if (monsterList && monsterList.length > 0) {
            listItems.push(
                <div className={Mstyle.dangerHeaderBox}>
                    <p className={Mstyle.dangerHeader}>
                        {letter}
                    </p>
                </div>
            );
            monsterList.forEach(monster => {
                listItems.push(
                    <div
                        key={monster.id}
                        onClick={() => handleClick(monster)}
                        className={Mstyle.bestiaryBox}
                    >
                        <p className={style.monsterLevel}>
                            {monster.danger > 100 ? "1/" + (monster.danger - 100) : monster.danger}
                        </p>
                        <p className={style.monsterName}>
                            {monster.name.length > 14
                                ? monster.name.substring(0, 20) + "..."
                                : monster.name}
                        </p>
                    </div>
                );
            });
        }
    }

    return <>{listItems}</>;
}

export default MonsterSmall;