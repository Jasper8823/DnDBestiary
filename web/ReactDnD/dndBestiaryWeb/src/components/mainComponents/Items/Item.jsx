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

    return(
        <div key={item.name}>
            <p>{item.name}</p>
        </div>
    )
}

export default Item;