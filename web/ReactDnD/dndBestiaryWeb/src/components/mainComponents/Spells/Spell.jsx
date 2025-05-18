import { useParams } from 'react-router-dom';
import style from './spells.module.css'

function Spell(props){
    const {id} = useParams();
    const spell = props.spells[parseInt(id)];
    return(
        <p>{spell.name}</p>
    )
}

export default Spell;