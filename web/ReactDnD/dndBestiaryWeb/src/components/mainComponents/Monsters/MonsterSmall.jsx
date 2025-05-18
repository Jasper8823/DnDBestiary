import { useNavigate, useEffect } from "react-router-dom";
import style from './monsters.module.css';

function MonsterSmall(props) {
  const navigate = useNavigate();

  const handleClick = (monster) => {
    navigate(`/bestiary/${monster.id}`);
  };

  
  const listItems = monsters.map(monster => (
    <div key={monster.id} onClick={() => handleClick(monster)} className={style.monsterBox}>
      <p className={style.monsterLevel}>{monster.lvl}</p>
      <p className={style.monsterName}>
        {monster.name.length > 20 ? monster.name.substring(0, 20) + "..." : monster.name}
      </p>
    </div>
  ));

  //   useEffect(() => {
  //       fetch('/api/data')
  //           .then(response => response.text())
  //           .then(data => setData(data))
  //           .catch(error => console.error('Error fetching data:', error));
  // }, []);

  // const listItems = data.monsters.map(monster => (
  //   <div key={monster.id} onClick={() => handleClick(monster)} className={style.monsterBox}>
  //     <p className={style.monsterLevel}>{monster.danger}</p>
  //     <p className={style.monsterName}>
  //       {monster.name.length > 20 ? monster.name.substring(0, 20) + "..." : monster.name}
  //     </p>
  //   </div>
  // ));

  return <>{listItems}</>;
}

export default MonsterSmall;
