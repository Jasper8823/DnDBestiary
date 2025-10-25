import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import style from './items.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';
import swordImage from '../images/sword.png';
import staffImage from '../images/staff.png';
import armorImage from '../images/armor.png';
import rodImage from '../images/rod.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFlask, faScroll, faGem } from '@fortawesome/free-solid-svg-icons';
import { faWandSparkles } from '@fortawesome/free-solid-svg-icons'
import { faRing } from '@fortawesome/free-solid-svg-icons';

const rarity = ['common', 'uncommon', 'rare', 'veryRare', 'legendary', 'artifact', 'noConstRarity'];

function ItemSmall() {
        const { userid } = useParams();
      const navigate = useNavigate();
        const [items, setItems] = useState(null);

      const handleClick = (item) => {
        navigate(`/items/${item.name}`);
      };


      useEffect(() => {
            const query = location.search;
            fetch(`http://localhost:8080/getItems${query}`, {
            method: 'GET',
            })
            .then(res => res.json())
            .then(data => setItems(data))
            .catch(err => console.error('Failed to load items:', err));
      }, [location.search]);

      if (!items) return <p>Loading items...</p>;


      let listItems = [];
  
      for (let i = 0; i < rarity.length; i++) {
          const level = rarity[i];
          const itemList = items[level];
          if (itemList && itemList.length > 0) {
              listItems.push(
                  <div className={Mstyle.dangerHeaderBox}>
                      <p className={Mstyle.dangerHeader}>
                          {rarity[i]}
                      </p>
                  </div>
              );
              itemList.forEach(item => {
                  listItems.push(
                    <div key={item.id} onClick={() => handleClick(item)} className={Mstyle.bestiaryBox}>
                        {item.type === "armor" ? <img src={armorImage} className={style.picture}/> :
                        item.type === "weapon" ? <img src={swordImage} className={style.picture}/> :
                        item.type === "potion" ? <FontAwesomeIcon icon={faFlask} className={style.icon}/> :
                        item.type === "ring" ? <FontAwesomeIcon icon={faRing} className={style.icon}/> :
                        item.type === "scroll" ? <FontAwesomeIcon icon={faScroll} className={style.icon}/> :
                        item.type === "rod" ? <img src={rodImage} className={style.picture}/> :
                        item.type === "wand" ? <FontAwesomeIcon icon={faWandSparkles} className={style.icon}/> :
                        item.type === "staff" ? <img src={staffImage} className={style.picture}/> :
                        item.type === "wonderful item" ? <FontAwesomeIcon icon={faGem} className={style.icon}/> :
                        <p className={style.itemName}>Error</p>}
                        <p className={style.itemName}>
                            {item.name.length > 14 ? item.name.substring(0, 20) + "..." : item.name}
                        </p>
                    </div>
                  );
              });
          }
      }

      return <>{listItems}</>;
}

export default ItemSmall;
