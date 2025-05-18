import style from './mainStyle.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSkull } from '@fortawesome/free-solid-svg-icons'
import { faWandSparkles } from '@fortawesome/free-solid-svg-icons'
import { faUser } from '@fortawesome/free-solid-svg-icons'
import swordImage from './images/sword.png'
import battleImage from './images/battle.png'


function SideBar(){
    return(
        <div className={style.sideBarBox}>
            <div className={style.subBox}>
                <div className={style.titleBox}>
                    <h3>Directories</h3>
                </div>
                <div className={style.directBox}>
                    <FontAwesomeIcon icon={faSkull} className={style.icon}/><p>Bestiary</p>
                </div>
                <div className={style.directBox}>
                    <FontAwesomeIcon icon={faWandSparkles} className={style.icon}/><p>Spells</p>
                </div>
                <div className={style.directBox}>
                    <img src={swordImage} className={style.picture}/><p>Items</p>
                </div>
            </div>
            <div className={style.subBox}>
                <div className={style.titleBox}>
                    <h3>Tools</h3>
                </div>
                <div className={style.directBox}>
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