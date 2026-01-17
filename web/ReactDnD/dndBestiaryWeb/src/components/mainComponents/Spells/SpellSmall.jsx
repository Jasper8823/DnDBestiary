import { useParams } from 'react-router-dom';
import { useNavigate, useLocation } from "react-router-dom";
import style from './spells.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function SpellSmall() {
        const { userid } = useParams();
        const navigate = useNavigate();
        const location = useLocation();
        const [spells, setSpells] = useState(null);

        if(userid){
            const updateUserId = (async () =>{

                try {
                    const response = await fetch(`http://localhost:8080/prolong?userid=${userid}`, {
                        method: "POST",
                    });
                    const rawText = await response.text();
                    if(rawText == "1"){
                        navigate(`/`);
                    }
                } catch (error) {
                    console.error("Error:", error);
                    alert("Error while sending request.");
                }
            })

            updateUserId();
        }

        const handleClick = (spell) => {
          if(userid){
            navigate(`/${userid}/spells/${spell.name}`);
          }else{
            navigate(`/spells/${spell.name}`);
          }
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


        let listItems = [];
    

        for (let i = 65; i <= 90; i++) { 
            let letter = String.fromCharCode(i);
            const spellList = spells[letter];
            if (spellList && spellList.length > 0) {
                listItems.push(
                    <div className={Mstyle.dangerHeaderBox}>
                        <p className={Mstyle.dangerHeader}>
                            {letter}
                        </p>
                    </div>
                );
                spellList.forEach(spell => {
                    listItems.push(
                      <div key={spell.id} onClick={() => handleClick(spell)} className={Mstyle.bestiaryBox}>
                          <p className={style.spellLevel}>{spell.level}</p>
                          <p className={style.spellName}>
                              {spell.name.length > 12 ? spell.name.substring(0, 12) + "..." : spell.name}
                          </p>
                      </div>
                    );
                });
            }
        }
        
        return <>{listItems}</>;
}

export default SpellSmall;
