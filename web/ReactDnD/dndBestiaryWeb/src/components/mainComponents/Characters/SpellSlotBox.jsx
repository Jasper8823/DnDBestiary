import { useParams } from 'react-router-dom';
import style from './character.module.css'

function SpellSlotBox(props){
    const statName = props.name;
    let statValue = props.value;
    const isGray = props.isGrey;

    if(!statValue){
        statValue="0";
    }

    return(
        <div className={style.spellBox} style = {isGray ? {backgroundColor: 'rgba(100,100,100,0.15)'} : {backgroundColor: 'rgba(100,100,100,0)'}}>
            <p className={style.spellBoxName}>{statName}</p>
            <p>{statValue}</p>
        </div>
    )
}

export default SpellSlotBox;