import { useNavigate, useLocation } from "react-router-dom";
import style from './spells.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function SpellSmall() {
  const navigate = useNavigate();
  const location = useLocation();
  const [spells, setSpells] = useState(null);

  const handleClick = (spell) => {
    navigate(`/spells/${spell.name}`);
  };

  useEffect(() => {
    const query = location.search;
    fetch(`http://localhost:8080/getSpells${query}`, {
      method: 'GET',
    })
    .then(res => res.json())
    .then(data => setSpells(data))
    .catch(err => console.error('Failed to load spells:', err));
  }, [location.search]);

  if (!spells) return <p>Loading spells...</p>;

  const listItems = spells.map(spell => (
    <div key={spell.id} onClick={() => handleClick(spell)} className={Mstyle.bestiaryBox}>
      <p className={style.spellLevel}>{spell.level != 0 ? spell.level : "plot"}</p>
      <p className={style.spellName}>
        {spell.name.length > 20 ? spell.name.substring(0, 20) + "..." : spell.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default SpellSmall;
