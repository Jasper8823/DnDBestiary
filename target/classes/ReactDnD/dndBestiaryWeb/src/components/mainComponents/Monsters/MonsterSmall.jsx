import { useNavigate } from "react-router-dom";
import style from './monsters.module.css';

function MonsterSmall(props) {
  const navigate = useNavigate();

  const handleClick = (monster) => {
    navigate(`/bestiary/${monster.id}`);
  };

  const listItems = props.monsters.map(monster => (
    <div key={monster.id} onClick={() => handleClick(monster)} className={style.monsterBox}>
      <p className={style.monsterLevel}>{monster.lvl}</p>
      <p className={style.monsterName}>
        {monster.name.length > 20 ? monster.name.substring(0, 20) + "..." : monster.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default MonsterSmall;
