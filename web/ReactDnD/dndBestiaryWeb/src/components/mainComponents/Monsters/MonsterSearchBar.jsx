import { useState } from 'react';
import style from './monsters.module.css';
import Mstyle from '../mainStyle.module.css';
import CustomDropdown from '../CustomDropdown.jsx';
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
    const handleChange = (name, values) => {
    switch (name) {
        case "name":
        setFormData({
            ...formData,
            name: values,
        });
        break;

        case "size":
        setSizes(values);
        setFormData({
            ...formData,
            size: values,
        });
        break;

        case "type":
        setTypes(values);
        setFormData({
            ...formData,
            type: values,
        });
        break;

        case "worldView":
        setWorldViews(values);
        setFormData({
            ...formData,
            worldView: values,
        });
        break;

        case "danger":
        setDangers(values);
        setFormData({
            ...formData,
            danger: values,
        });
        break;

        default:
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
                <input
                    className={Mstyle.searchInput}
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={(e) => handleChange("name", e.target.value)}
                    minLength={3}
                    maxLength={32}
                    placeholder={formData.name || "Name"}
                />
                <CustomDropdown
                            name="size"
                            options={sizeOptions}
                            selectedValues={formData.size}
                            onChange={handleChange}
                            placeholder="Size"
                            idName = "searchSize"
                />

                <CustomDropdown
                            name="type"
                            options={typeOptions}
                            selectedValues={formData.type}
                            onChange={handleChange}
                            placeholder="Type"
                            idName = "searchType"
                />

                <CustomDropdown
                            name="worldView"
                            options={worldViewOptions}
                            selectedValues={formData.worldView}
                            onChange={handleChange}
                            placeholder="Worldview"
                            idName = "searchWV"
                />

                <CustomDropdown
                            name="danger"
                            options={dangerOptions}
                            selectedValues={formData.danger}
                            onChange={handleChange}
                            placeholder="Danger"
                            idName = "searchDanger"
                />

                <button className={Mstyle.searchButton} type="submit">Search</button>
                <button className={Mstyle.searchButton} type="submit" onClick={handleReset}>Reset</button>
            </form>
        </div>
    );
}

export default MonsterSearchBar;