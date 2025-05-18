import { useParams } from 'react-router-dom';
import style from './monsters.module.css'

function Monster(props){
    const {id} = useParams();
    const monster = props.monsters[parseInt(id)];
    return(
        <p>{monster.name}</p>
    )
}

export default Monster;