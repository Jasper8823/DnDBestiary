import { useNavigate } from "react-router-dom";
import style from './items.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function ItemSmall() {
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

  const listItems = items.map(item => (
    <div key={item.id} onClick={() => handleClick(item)} className={Mstyle.bestiaryBox}>
      <p className={style.itemType}>{item.type}</p>
      <p className={style.itemName}>
        {item.name.length > 20 ? item.name.substring(0, 20) + "..." : item.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default ItemSmall;
