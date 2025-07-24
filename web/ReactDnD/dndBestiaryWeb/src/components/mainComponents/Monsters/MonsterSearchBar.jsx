import { useState } from 'react';
import style from './monsters.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

function MonsterSearchBar() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        size: '',
        type: '',
        worldView: '',
        danger: '',
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
        fetch('http://localhost:8080/getMosters/sort', {
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
            size: '',
            type: '',
            worldView: '',
            danger: '',
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input className={Mstyle.searchInput} id={style.searchInput} type="text" name="name" value={formData.name} onChange={handleChange} minLength={3} maxLength={32} required placeholder="Name"/>

                <select className={Mstyle.searchSelect} id={style.searchSize} name="size" value={formData.size} onChange={handleChange} required >
                    <option value="" disabled selected>Size</option>
                    <option value="tiny">Tiny</option>
                    <option value="small">Small</option>
                    <option value="medium">Medium</option>
                    <option value="large">Large</option>
                    <option value="huge">Huge</option>
                    <option value="colossal">Colossal</option>
                    <option value="sstiny">Small Swarm of Tiny</option>
                    <option value="mstiny">Medium Swarm of Tiny</option>
                    <option value="lstiny">Large Swarm of Tiny</option>
                    <option value="lssmall">Large Swarm of Small</option>
                    <option value="lsmedium">Large Swarm of Medium</option>
                </select>

                <select className={Mstyle.searchSelect} id={style.searchType} name="type" value={formData.type} onChange={handleChange} required >
                    <option value="" disabled selected>Type</option>
                    <option value="aberration">Aberration</option>
                    <option value="giant">Giant</option>
                    <option value="humanoid">Humanoid</option>
                    <option value="dragon">Dragon</option>
                    <option value="beast">Beast</option>
                    <option value="demon">Demon</option>
                    <option value="construct">Construct</option>
                    <option value="monster">Monster</option>
                    <option value="celestial">Celestial</option>
                    <option value="undead">Undead</option>
                    <option value="plant">Plant</option>
                    <option value="ooze">Ooze</option>
                    <option value="pixie">Pixie</option>
                    <option value="elemental">Elemental</option>
                </select>

                <select className={Mstyle.searchSelect} id={style.searchWV} name="worldView" value={formData.worldView} onChange={handleChange} required >
                    <option value="" disabled selected>Worldview</option>
                    <option value="lawful_good">Lawful Good</option>
                    <option value="neutral_good">Neutral Good</option>
                    <option value="chaotic_good">Chaotic Good</option>
                    <option value="lawful_neutral">Lawful Neutral</option>
                    <option value="true_neutral">True Neutral</option>
                    <option value="chaotic_neutral">Chaotic Neutral</option>
                    <option value="lawful_evil">Lawful Evil</option>
                    <option value="neutral_evil">Neutral Evil</option>
                    <option value="chaotic_evil">Chaotic Evil</option>
                    <option value="noWV">No Worldview</option>
                </select>

                <select className={Mstyle.searchSelect} id={style.searchDanger} name="danger" value={formData.danger} onChange={handleChange} required >
                    <option value="" disabled selected>Danger</option>
                    <option value="100">0 - 10 exp</option>
                    <option value="108">1/8 - 25 exp</option>
                    <option value="104">1/4 - 50 exp</option>
                    <option value="102">1/2 - 100 exp</option>
                    <option value="1">1 - 200 exp</option>
                    <option value="2">2 - 450 exp</option>
                    <option value="3">3 - 700 exp</option>
                    <option value="4">4 - 1100 exp</option>
                    <option value="5">5 - 1800 exp</option>
                    <option value="6">6 - 2300 exp</option>
                    <option value="7">7 - 2900 exp</option>
                    <option value="8">8 - 3900 exp</option>
                    <option value="9">9 - 5000 exp</option>
                    <option value="10">10 - 5900 exp</option>
                    <option value="11">11 - 7200 exp</option>
                    <option value="12">12 - 8400 exp</option>
                    <option value="13">13 - 10 000 exp</option>
                    <option value="14">14 - 11 500 exp</option>
                    <option value="15">15 - 13 000 exp</option>
                    <option value="16">16 - 15 000 exp</option>
                    <option value="17">17 - 18 000 exp</option>
                    <option value="18">18 - 20 000 exp</option>
                    <option value="19">19 - 22 000 exp</option>
                    <option value="20">20 - 25 000 exp</option>
                    <option value="21">21 - 33 000 exp1</option>
                    <option value="22">22 - 41 000 exp</option>
                    <option value="23">23 - 50 000 exp</option>
                    <option value="24">24 - 62 000 exp</option>
                    <option value="25">25 - 75 000 exp</option>
                    <option value="26">26 - 90 000 exp</option>
                    <option value="27">27 - 105 000 exp</option>
                    <option value="28">28 - 120 000 exp</option>
                    <option value="29">29 - 135 000 exp</option>
                    <option value="30">30 - 155 000 exp</option>
                </select>

                <button className={Mstyle.searchButton} type="button" onClick={handleReset}>Reset</button>
                <button className={Mstyle.searchButton} type="submit">Search</button>
            </form>
        </div>
    );
}

export default MonsterSearchBar;