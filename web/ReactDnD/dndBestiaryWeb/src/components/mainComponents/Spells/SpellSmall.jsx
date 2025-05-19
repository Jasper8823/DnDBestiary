import { useNavigate } from "react-router-dom";
import style from './spells.module.css';
import { useEffect, useState } from 'react';
import { faAlignCenter } from "@fortawesome/free-solid-svg-icons";

function SpellSmall() {
  const navigate = useNavigate();
    const [spells, setSpells] = useState(null);

  const handleClick = (spell) => {
    navigate(`/spells/${spell.name}`);
  };

  useEffect(() => {
  fetch('http://localhost:8080/getSpells')
    .then(response => response.json())
    .then(data => {
      console.log("Fetched spells:", data);
      setSpells(data);
    })
    .catch(error => console.error('Error fetching data:', error));
}, []);

  if (!spells) return <p>Loading spells...</p>;

  const listItems = spells.map(spell => (
    <div key={spell.name} onClick={() => handleClick(spell)} className={style.spellBox}>
      <p className={style.spellLevel}>{spell.lvl}</p>
      <p className={style.spellName}>
        {spell.name.length > 20 ? spell.name.substring(0, 20) + "..." : spell.name}
      </p>
    </div>
  ));

  return <>{listItems}</>;
}

export default SpellSmall;
