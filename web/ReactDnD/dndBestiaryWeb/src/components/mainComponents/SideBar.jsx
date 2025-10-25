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

    const pathParts = location.pathname.split("/").filter(Boolean); 

    let bool = 0;

    if(pathParts[0]==="bestiary" || pathParts[0]==="items" || 
        pathParts[0]==="spells" || pathParts[0]==="characters" ||
        pathParts[0]==="create-character" || pathParts[0]==="combat-calculator" ||
        pathParts[0]==="login" || pathParts[0]==="signup"
    ){

    }else{
        bool = 1;
    }

    console.log(bool);

    const monsterRouter = () => {
        if(bool===1){
            navigate(`/${pathParts[[0]]}/bestiary`);
        }else{
            navigate(`/bestiary`);
        }
    };

    const itemRouter = () => {
        if(bool===1){
            navigate(`/${pathParts[[0]]}/items`);
        }else{
            navigate(`/items`);
        }
    };

    const spellRouter = () => {
        if(bool===1){
            navigate(`/${pathParts[[0]]}/spells`);
        }else{
            navigate(`/spells`);
        }
    };    
    
    const calcRouter = () => {
        if(bool===1){
            navigate(`/${pathParts[[0]]}/combat-calculator`);
        }else{
            navigate(`/combat-calculator`);
        }
    };

    const charRouter = () => {
        navigate(`/${pathParts[[0]]}/characters`);
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
                {bool === 1 &&
                    <div className={style.directBox} onClick={() => charRouter()}>
                        <FontAwesomeIcon icon={faUser} className={style.icon}/><p>Characters</p>
                    </div>
                }
            </div>
        </div>
    )
}

export default SideBar;