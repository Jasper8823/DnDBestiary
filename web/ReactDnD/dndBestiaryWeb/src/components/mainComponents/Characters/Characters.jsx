import { useNavigate } from "react-router-dom";
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function Characters() {
    const navigate = useNavigate();
    const [characters, setCharacters] = useState(null);

    const handleClick = () => {
        navigate(`/create-character`);
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
    let listCharacters;

    if (!characters) listCharacters = "Loading chars..."
    else{
        listCharacters = characters.map(char => (
            <div key={char.id} onClick={() => handleClick(char)} className={Mstyle.bestiaryBox}>
            <p className={style.charType}>{char.type}</p>
            <p className={style.charName}>
                {char.name.length > 20 ? char.name.substring(0, 20) + "..." : char.name}
            </p>
            </div>
        ));
    }
    
    return <>
    <button id={style.createButton}  onClick={() => handleClick()}>Create Character</button>
    {listCharacters}
    </>;
}

export default Characters;