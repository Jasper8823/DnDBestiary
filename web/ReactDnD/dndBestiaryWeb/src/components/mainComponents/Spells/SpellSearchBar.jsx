import { useState } from 'react';
import style from './spells.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

function SpellSearchBar() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        level: '',
        charClass: '',
        type: ''
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
        const query = new URLSearchParams(formData).toString();
        navigate(`/spells?${query}`);
    };

    const handleReset = () => {
        setFormData({
            name: '',
            level: '',
            charClass: '',
            type: ''
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input className={Mstyle.searchInput} id={style.searchInput} type="text" name="name" value={formData.name} onChange={handleChange} minLength={3} maxLength={32}  placeholder="Name"/>

                <select className={Mstyle.searchSelect} name="level" value={formData.level} onChange={handleChange}>
                    <option value="" disabled selected>Level</option>
                    <option value="0">Plot</option>
                    <option value="1">1 level</option>
                    <option value="2">2 level</option>
                    <option value="3">3 level</option>
                    <option value="4">4 level</option>
                    <option value="5">5 level</option>
                    <option value="6">6 level</option>
                    <option value="7">7 level</option>
                    <option value="8">8 level</option>
                    <option value="9">9 level</option>
                </select>

                <select className={Mstyle.searchSelect} name="charClass" value={formData.charClass} onChange={handleChange}>
                    <option value="" disabled selected>Class</option>
                    <option value="bard">Bard</option>
                    <option value="cleric">Cleric</option>
                    <option value="druid">Druid</option>
                    <option value="sorcerer">Sorcerer</option>
                    <option value="warlock">Warlock</option>
                    <option value="wizard">Wizard</option>
                </select>

                <select className={Mstyle.searchSelect} id={style.searchType} name="type" value={formData.type} onChange={handleChange}   >
                    <option value="" disabled selected>Type</option>
                    <option value="abjuration">Abjuration</option>
                    <option value="conjuration">Conjuration</option>
                    <option value="divination">Divination</option>
                    <option value="enchantment">Enchantment</option>
                    <option value="evocation">Evocation</option>
                    <option value="illusion">Illusion</option>
                    <option value="necromancy">Necromancy</option>
                    <option value="transmutation">Transmutation</option>
                </select>

                <button className={Mstyle.searchButton} type="button" onClick={handleReset}>Reset</button>
                <button className={Mstyle.searchButton} type="submit">Search</button>
            </form>
        </div>
    );
}

export default SpellSearchBar;