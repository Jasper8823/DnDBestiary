import { useNavigate } from "react-router-dom";
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function Characters() {
    const navigate = useNavigate();
    const [characters, setCharacters] = useState(null);

    const handleClickCreate = () => {
        navigate(`/create-character`);
    };

    const handleClick = (char) => {
        navigate(`/characters/${char.id}`);
    };

    useEffect(() => {
            const query = location.search;
            fetch(`http://localhost:8080/getCharacters`, {
            method: 'GET',
            })
            .then(res => res.json())
            .then(data => setCharacters(data))
            .catch(err => console.error('Failed to load characters:', err));
    }, [location.search]);
    let listCharacters = [];

    if (!characters) listCharacters = "Loading chars..."
    else{
        for (let i = 65; i <= 90; i++) { 
                let letter = String.fromCharCode(i);
                const characterList = characters[letter];
                if (characterList && characterList.length > 0) {
                    listCharacters.push(
                        <div className={Mstyle.dangerHeaderBox}>
                            <p className={Mstyle.dangerHeader}>
                                {letter}
                            </p>
                        </div>
                    );
                    characterList.forEach(char => {
                        listCharacters.push(
                            <div key={char.id} onClick={() => handleClick(char)} className={Mstyle.bestiaryBox}>
                            <p className={style.charLevel}>{char.level}</p>
                            <p className={style.charName}>
                                {char.name.length > 20 ? char.name.substring(0, 20) + "..." : char.name}
                            </p>
                            </div>
                    )});
            }
        }
    }
    return <>
    <button id={style.createButton}  onClick={() => handleClickCreate()}>Create Character</button>
    {listCharacters}
    </>;
}

export default Characters;