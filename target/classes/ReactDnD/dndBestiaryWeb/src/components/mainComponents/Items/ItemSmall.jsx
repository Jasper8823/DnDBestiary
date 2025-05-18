import { useNavigate } from "react-router-dom";
import style from './items.module.css';

function ItemSmall(props) {
  const navigate = useNavigate();

  const handleClick = (item) => {
    navigate(`/items/${item.id}`);
  };

  const listItems = props.items.map(item => (
    <div key={item.id} onClick={() => handleClick(item)} className={style.monsterBox}>
      <p className={style.monsterLevel}>{item.type}</p>
      <p className={style.monsterName}>
        {item.name.length > 20 ? item.name.substring(0, 20) + "..." : item.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default ItemSmall;
