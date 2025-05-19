import { useParams } from 'react-router-dom';
import style from './spells.module.css'
import { useEffect, useState } from 'react';

function Spell(){
    const {id} = useParams();
    const [spell, setSpell] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/getSpell?id=${id}`)
            .then(response => response.json())
            .then(data => setSpell(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    if (!spell) return <p>Loading spell...</p>;

    return(
        <div key={spell.name}>
            <p >{spell.name}</p>
        </div>
    )
}

export default Spell;