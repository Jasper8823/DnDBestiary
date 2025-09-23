import { useState } from 'react';
import style from './items.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

const rarityOptions = {
  common: "Common",
  uncommon: "Uncommon",
  rare: "Rare",
  veryRare: "Very Rare",
  legendary: "Legendary",
  artifact: "Artifact"
};

const typeOptions = {
  wand: "Wand",
  armor: "Armor",
  rod: "Rod",
  potion: "Potion",
  ring: "Ring",
  weapon: "Weapon",
  staff: "Staff",
  scroll: "Scroll",
  "wondrous-item": "Wondrous Item"
};

const configOptions = {
  true: "Needs configuration",
  false: "Doesn't need configuration"
};

function ItemSearchBar() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: '',
    rarity: [],
    type: [],
    needsAdjustment: []
  });

  const [rarities, setRarities] = useState([]);
  const [types, setTypes] = useState([]);
  const [configs, setConfigs] = useState([]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    switch(name) {
      case "name":
        setFormData({ ...formData, [name]: value });
        break;
      case "rarity":
        if (rarities.includes(value)) {
            setRarities(rarities.filter((s) => s !== value));
        } else {
            setRarities([...rarities, value]);
        }
        break;
      case "type":
        if (types.includes(value)) {
            setTypes(types.filter((s) => s !== value));
        } else {
            setTypes([...types, value]);
        }
        break;
      case "needsAdjustment":
        if (configs.includes(value)) {
            setConfigs(configs.filter((s) => s !== value));
        } else {
            setConfigs([...configs, value]);
        }
        break;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormData({
      ...formData,
      rarity: rarities,
      type: types,
      needsAdjustment: configs
    });
    const query = new URLSearchParams(formData).toString();
    navigate(`/items?${query}`);
  };

  const handleReset = () => {
    setFormData({ name: '', rarity: [], type: [], needsAdjustment: [] });
    setRarities([]);
    setTypes([]);
    setConfigs([]);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          className={Mstyle.searchInput}
          id={style.searchInput}
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          placeholder="Name"
        />

        <select className={Mstyle.searchSelect} name="rarity" onChange={handleChange}>
          <option value="" disabled>Rarity</option>
          {Object.entries(rarityOptions).map(([value, label]) => (
            <option key={value} value={value} className={rarities.includes(value) ? style.selectedOption : ""}>
              {label}
            </option>
          ))}
        </select>

        <select className={Mstyle.searchSelect} name="type" onChange={handleChange}>
          <option value="" disabled>Type</option>
          {Object.entries(typeOptions).map(([value, label]) => (
            <option key={value} value={value} className={types.includes(value) ? style.selectedOption : ""}>
              {label}
            </option>
          ))}
        </select>

        <select className={Mstyle.searchSelect} id={style.configSearch} name="needsAdjustment" onChange={handleChange}>
          <option value="" disabled>Configuration</option>
          {Object.entries(configOptions).map(([value, label]) => (
            <option key={value} value={value} className={configs.includes(value) ? style.selectedOption : ""}>
              {label}
            </option>
          ))}
        </select>

        <button type="submit" className={Mstyle.searchButton}>Search</button>
        <button type="button" className={Mstyle.searchButton} onClick={handleReset}>Reset</button>
      </form>
    </div>
  );
}

export default ItemSearchBar;
