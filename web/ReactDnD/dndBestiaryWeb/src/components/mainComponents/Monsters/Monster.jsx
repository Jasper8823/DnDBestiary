import { useParams } from 'react-router-dom';
import style from './monsters.module.css'
import StatBox from './StatBox';

function Monster(props){
    const {id} = useParams();
    const monster = props.monsters[parseInt(id)];
    return(
        <div className={style.mainBox}>
            <p id={style.monsterName}><b>{monster.name}</b></p>
            <div className={style.sizeTypeWVBox}>
                <p><i>Big</i></p>
                <p>Ozee</p>
                <p>Chaoticly good</p>
            </div>
            <p><b>Armor type</b> 11</p>
            <p><b>Hit points</b> 26 (4d8+8)</p>
            <p><b>Speed</b> 20</p>
            <StatBox name = {"Str"} value = {10} isGrey = {true}/>
            <StatBox name = {"Str"} value = {22} isGrey = {false}/>
            <StatBox name = {"Str"} value = {9} isGrey = {true}/>
            <StatBox name = {"Str"} value = {10} isGrey = {false}/>
            <StatBox name = {"Str"} value = {22} isGrey = {true}/>
            <StatBox name = {"Str"} value = {9} isGrey = {false}/>
            <p><b>Danger</b> 2 (450)</p>
            <p className = {style.features}><b>Features</b></p>
        </div>
    )
}

export default Monster;