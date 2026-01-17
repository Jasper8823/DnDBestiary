import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function Characters() {
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

    const handleClickCreate = () => {
        navigate(`/${userid}/create-character`);
    };

    const handleClick = (char) => {
        navigate(`/${userid}/characters/${char.id}`);
    };

    useEffect(() => {
            const query = location.search;
            fetch(`http://localhost:8080/getCharacters?userid=${userid}`, {
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
                                {char.name.length > 14 ? char.name.substring(0, 14) + "..." : char.name}
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