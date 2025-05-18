import { useParams } from 'react-router-dom';
import style from './items.module.css'

function Item(props){
    const {id} = useParams();
    const item = props.items[parseInt(id)];
    return(
        <p>{item.name}</p>
    )
}

export default Item;