import { useState } from 'react';
import style from './items.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

function ItemSearchBar() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        rarity: '',
        type: '',
        needsAdjustment: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/getItems/sort', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            console.log('Request sent:', formData);
        }).catch(error => {
            console.error('Error sending request:', error);
        });
    };

    const handleReset = () => {
        setFormData({
            name: '',
            rarity: '',
            type: '',
            needsAdjustment: ''
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input className={Mstyle.searchInput} id={style.searchInput} type="text" name="name" value={formData.name} onChange={handleChange} minLength={3} maxLength={32} required placeholder="Name"/>

                <select className={Mstyle.searchSelect} name="rarity" value={formData.rarity} onChange={handleChange} required >
                    <option value="" disabled selected>Rarity</option>
                    <option value="common">Common</option>
                    <option value="uncommon">Uncommon</option>
                    <option value="rare">Rare</option>
                    <option value="veryRare">Very Rare</option>
                    <option value="legendary">Legendary</option>
                    <option value="artifact">Artifact</option>
                </select>

                <select className={Mstyle.searchSelect} name="type" value={formData.type} onChange={handleChange} required >
                    <option value="" disabled selected>Type</option>
                    <option value="wand">Wand</option>
                    <option value="armor">Armor</option>
                    <option value="rod">Rod</option>
                    <option value="potion">Potion</option>
                    <option value="ring">Ring</option>
                    <option value="weapon">Weapon</option>
                    <option value="staff">Staff</option>
                    <option value="scroll">Scroll</option>
                    <option value="wondrous-item">Wondrous Item</option>
                </select>

                <select className={Mstyle.searchSelect} id={style.searchConf} name="needsAdjustment" value={formData.needsAdjustment} onChange={handleChange} required >
                    <option value="" disabled selected>Configuration</option>
                    <option value="true">Needs configuration</option>
                    <option value="false">Doesn't need configuration</option>
                </select>

                <button className={Mstyle.searchButton} type="button" onClick={handleReset}>Reset</button>
                <button className={Mstyle.searchButton} type="submit">Search</button>
            </form>
        </div>
    );
}

export default ItemSearchBar;