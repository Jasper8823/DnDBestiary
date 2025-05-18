import { useParams } from 'react-router-dom';
import style from './monsters.module.css'

function StatBox(props){
    const statName = props.name;
    const statValue = props.value;
    const isGray = props.isGrey;
    let modif = '';
    if(Math.floor(((statValue-10)/2))>0){
        modif = '+';
        modif += Math.floor((statValue-10)/2);
    }else{
        modif += Math.floor((statValue-10)/2);
    }

    return(
        <div className={style.statBox} style = {isGray ? {backgroundColor: 'rgba(100,100,100,0.15)'} : {backgroundColor: 'rgba(100,100,100,0)'}}>
            <p>{statName}</p>
            <p>{statValue} ({modif})</p>
        </div>
    )
}

export default StatBox;