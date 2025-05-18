import { useNavigate } from "react-router-dom";
import style from './spells.module.css';

function SpellSmall(props) {
  const navigate = useNavigate();

  const handleClick = (spell) => {
    navigate(`/spells/${spell.id}`);
  };

  const listItems = props.spells.map(spell => (
    <div key={spell.id} onClick={() => handleClick(spell)} className={style.monsterBox}>
      <p className={style.monsterLevel}>{spell.lvl}</p>
      <p className={style.monsterName}>
        {spell.name.length > 20 ? spell.name.substring(0, 20) + "..." : spell.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default SpellSmall;
