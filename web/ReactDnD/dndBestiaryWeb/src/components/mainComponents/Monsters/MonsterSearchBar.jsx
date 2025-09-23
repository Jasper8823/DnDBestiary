import { useState } from 'react';
import style from './monsters.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

const sizeOptions = {
  tiny: "Tiny",
  small: "Small",
  medium: "Medium",
  large: "Large",
  huge: "Huge",
  colossal: "Colossal",
  sstiny: "Small Swarm of Tiny",
  mstiny: "Medium Swarm of Tiny",
  lstiny: "Large Swarm of Tiny",
  lssmall: "Large Swarm of Small",
  lsmedium: "Large Swarm of Medium"
};

const typeOptions = {
  aberration: "Aberration",
  giant: "Giant",
  humanoid: "Humanoid",
  dragon: "Dragon",
  beast: "Beast",
  demon: "Demon",
  construct: "Construct",
  monster: "Monster",
  celestial: "Celestial",
  undead: "Undead",
  plant: "Plant",
  ooze: "Ooze",
  pixie: "Pixie",
  elemental: "Elemental"
};

const worldViewOptions = {
  lawful_good: "Lawful Good",
  neutral_good: "Neutral Good",
  chaotic_good: "Chaotic Good",
  lawful_neutral: "Lawful Neutral",
  true_neutral: "True Neutral",
  chaotic_neutral: "Chaotic Neutral",
  lawful_evil: "Lawful Evil",
  neutral_evil: "Neutral Evil",
  chaotic_evil: "Chaotic Evil",
  noWV: "No Worldview"
};

const dangerOptions = {
  100: "0 - 10 exp",
  108: "1/8 - 25 exp",
  104: "1/4 - 50 exp",
  102: "1/2 - 100 exp",
  1: "1 - 200 exp",
  2: "2 - 450 exp",
  3: "3 - 700 exp",
  4: "4 - 1100 exp",
  5: "5 - 1800 exp",
  6: "6 - 2300 exp",
  7: "7 - 2900 exp",
  8: "8 - 3900 exp",
  9: "9 - 5000 exp",
  10: "10 - 5900 exp",
  11: "11 - 7200 exp",
  12: "12 - 8400 exp",
  13: "13 - 10 000 exp",
  14: "14 - 11 500 exp",
  15: "15 - 13 000 exp",
  16: "16 - 15 000 exp",
  17: "17 - 18 000 exp",
  18: "18 - 20 000 exp",
  19: "19 - 22 000 exp",
  20: "20 - 25 000 exp",
  21: "21 - 33 000 exp",
  22: "22 - 41 000 exp",
  23: "23 - 50 000 exp",
  24: "24 - 62 000 exp",
  25: "25 - 75 000 exp",
  26: "26 - 90 000 exp",
  27: "27 - 105 000 exp",
  28: "28 - 120 000 exp",
  29: "29 - 135 000 exp",
  30: "30 - 155 000 exp"
};


function MonsterSearchBar() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        size: [],
        type: [],
        worldView: [],
        danger: [],
    });

    const [sizes, setSizes] = useState([]);
    const [types, setTypes] = useState([]);
    const [worldViews, setWorldViews] = useState([]);
    const [dangers, setDangers] = useState([]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        switch(name){
            case "name":
                setFormData({
                    ...formData,
                    [name]: value
                });
                break;
            case "size":
                if (sizes.includes(value)) {
                    setSizes(sizes.filter((s) => s !== value));
                } else {
                    setSizes([...sizes, value]);
                }
                break;
            case "type":
                if (types.includes(value)) {
                    setTypes(types.filter((s) => s !== value));
                } else {
                    setTypes([...types, value]);
                }
                break;
            case "worldView":
                if (worldViews.includes(value)) {
                    setWorldViews(worldViews.filter((s) => s !== value));
                } else {
                    setWorldViews([...worldViews, value]);
                }
                break;
            case "danger":
                if (dangers.includes(value)) {
                    setDangers(dangers.filter((s) => s !== value));
                } else {
                    setDangers([...dangers, value]);
                }
                break;
        }
    };


    const handleSubmit = (e) => {
        e.preventDefault();
        setFormData({
        ...formData,
        size: sizes,
        type: types,
        worldView: worldViews,
        danger: dangers
        });
        const query = new URLSearchParams(formData).toString();
        navigate(`/bestiary?${query}`);
    };

    const handleReset = () => {
        setFormData({
            name: ''
        });
        setSizes([]);
        setDangers([]);
        setWorldViews([]);
        setTypes([]);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input className={Mstyle.searchInput} id={style.searchInput} type="text" name="name" value={formData.name} onChange={handleChange} minLength={3} maxLength={32}   placeholder="Name"/>
                
                <select className={Mstyle.searchSelect} id={style.searchSize} name="size" onChange={handleChange}>
                    <option value="" disabled>Size</option>
                    {Object.entries(sizeOptions).map(([value, label]) => (
                        <option key={value} value={value} className={sizes.includes(value) ? style.selectedOption : ""} > {label} </option>
                    ))}
                </select>

                <select className={Mstyle.searchSelect} id={style.searchType} name="type" onChange={handleChange}>
                    <option value="" disabled>Type</option>
                    {Object.entries(typeOptions).map(([value, label]) => (
                        <option key={value} value={value} className={types.includes(value) ? style.selectedOption : ""} > {label} </option>
                    ))}
                </select>

                <select className={Mstyle.searchSelect} id={style.searchWV} name="worldView" onChange={handleChange}>
                    <option value="" disabled>Worldview</option>
                    {Object.entries(worldViewOptions).map(([value, label]) => (
                        <option key={value} value={value} className={worldViews.includes(value) ? style.selectedOption : ""} > {label} </option>
                    ))}
                </select>

                <select className={Mstyle.searchSelect} id={style.searchDanger} name="danger" onChange={handleChange}>
                    <option value="" disabled>Danger</option>
                    {Object.entries(dangerOptions).map(([value, label]) => (
                        <option key={value} value={value} className={dangers.includes(value) ? style.selectedOption : ""} > {label} </option>
                    ))}
                </select>

                <button className={Mstyle.searchButton} type="submit">Search</button>
                <button className={Mstyle.searchButton} type="button" onClick={handleReset}>Reset</button>
            </form>
        </div>
    );
}

export default MonsterSearchBar;