import style from './mainStyle.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSkull } from '@fortawesome/free-solid-svg-icons'
import { faWandSparkles } from '@fortawesome/free-solid-svg-icons'
import { faUser } from '@fortawesome/free-solid-svg-icons'
import swordImage from './images/sword.png'
import battleImage from './images/battle.png'
import { useNavigate } from "react-router-dom";

function SideBar(){
    const navigate = useNavigate();

    const monsterRouter = () => {
        navigate(`/bestiary`);
    };

    const itemRouter = () => {
        navigate(`/items`);
    };

    const spellRouter = () => {
        navigate(`/spells`);
    };    
    
    const calcRouter = () => {
        navigate(`/combat-calculator`);
    };

    return(
        <div className={style.sideBarBox}>
            <div className={style.subBox}>
                <div className={style.titleBox}>
                    <h3>Directories</h3>
                </div>
                <div className={style.directBox} onClick={() => monsterRouter()}>
                    <FontAwesomeIcon icon={faSkull} className={style.icon}/><p>Bestiary</p>
                </div>
                <div className={style.directBox} onClick={() => spellRouter()}>
                    <FontAwesomeIcon icon={faWandSparkles} className={style.icon}/><p>Spells</p>
                </div>
                <div className={style.directBox} onClick={() => itemRouter()}>
                    <img src={swordImage} className={style.picture}/><p>Items</p>
                </div>
            </div>
            <div className={style.subBox}>
                <div className={style.titleBox}>
                    <h3>Tools</h3>
                </div>
                <div className={style.directBox} onClick={() => calcRouter()}>
                    <img src={battleImage} className={style.picture}/><p>Combat calculator</p>
                </div>
                <div className={style.directBox}>
                    <FontAwesomeIcon icon={faUser} className={style.icon}/><p>Characters</p>
                </div>
            </div>
        </div>
    )
}

export default SideBar;