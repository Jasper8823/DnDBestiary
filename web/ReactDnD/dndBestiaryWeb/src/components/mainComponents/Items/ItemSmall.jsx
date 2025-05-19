import { useNavigate } from "react-router-dom";
import style from './items.module.css';
import { useEffect, useState } from 'react';

function ItemSmall() {
  const navigate = useNavigate();
    const [items, setItems] = useState(null);

  const handleClick = (item) => {
    navigate(`/items/${item.name}`);
  };

  useEffect(() => {
      fetch('http://localhost:8080/getItems')
          .then(response => response.json())
          .then(data => setItems(data))
          .catch(error => console.error('Error fetching data:', error));
  }, []);
    if (!items) return <p>Loading items...</p>;

  const listItems = items.map(item => (
    <div key={item.name} onClick={() => handleClick(item)} className={style.monsterBox}>
      <p className={style.itemType}>{item.type}</p>
      <p className={style.itemName}>
        {item.name.length > 20 ? item.name.substring(0, 20) + "..." : item.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default ItemSmall;
