import { useParams } from 'react-router-dom';
import style from './spells.module.css'
import { useEffect, useState } from 'react';

function Spell(props){
    const {id} = useParams();
    const [spell, setSpell] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/getSpell?id=${id}`)
            .then(response => response.json())
            .then(data => setSpell(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return(
        <p>{spell.name}</p>
    )
}

export default Spell;