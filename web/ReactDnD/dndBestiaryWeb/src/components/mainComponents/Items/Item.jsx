import { useParams } from 'react-router-dom';
import style from './items.module.css'
import { useEffect, useState } from 'react';

function Item(){
    const {id} = useParams();
    
    const [item, setItem] = useState(null);
    
    useEffect(() => {
        fetch(`http://localhost:8080/getItem?id=${id}`)
            .then(response => response.json())
            .then(data => setItem(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);
    
    if (!item) return <p>Loading item...</p>;

    let statuses = '';

    if(item.StatusList.length !==0){
        item.StatusList.forEach(status => {
            statuses+= `${status}, `
        });
        statuses = statuses.substring(0, statuses.length-2);
    }

    let damageDeal = '';

    if(item.DamageTypes.length !== 0){
        item.DamageTypes.forEach(damageT => {
            damageDeal+= `${damageT}, `
        });
        damageDeal = damageDeal.substring(0, damageDeal.length-2);
    }

    return(
        <div key={item.id} className={style.mainBox}>
            <p id={style.itemName}><b>{item.name}</b></p>
            <div className={style.shortDescBox}>
                <p><i>{item.item_type_name}</i></p>
                {item.subtype !== undefined && !isNaN(item.subtype) && <p>({item.subtype})</p>}
                <p>, {item.rarity_name}</p>
                {isNaN(item.configurable) && <p> (Need configuration)</p>}<br/>
            </div>
            {statuses.length !== 0 && <p>Statuses: {statuses}</p>}
            {damageDeal.length !== 0 && <p>Damage Types: {damageDeal}</p>}
            {isNaN(item.description) && <p className = {style.rows}><b>Description</b><br/></p>}
            <p>{item.description}</p>
        </div>
    )
}

export default Item;