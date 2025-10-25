import { useParams } from 'react-router-dom';
import { useState } from 'react';
import style from './items.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';
import CustomDropdown from '../CustomDropdown.jsx';

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
  const { userid } = useParams();

  const [formData, setFormData] = useState({
    name: '',
    rarity: [],
    type: [],
    needsAdjustment: []
  });

  const [rarities, setRarities] = useState([]);
  const [types, setTypes] = useState([]);
  const [configs, setConfigs] = useState([]);

  const handleChange = (name, values) => {
    switch(name) {
      case "name":
        setFormData({ ...formData, [name]: values });
        break;
      case "rarity":
        setRarities(values);
        setFormData({
            ...formData,
            rarity: values,
        });
        break;
      case "type":
        setTypes(values);
        setFormData({
            ...formData,
            type: values,
        });
        break;
      case "needsAdjustment":
        setConfigs(values);
        setFormData({
            ...formData,
            needsAdjustment: values,
        });
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
    if(userid){
      navigate(`/${userid}/items?${query}`);
    }else{
      navigate(`/items?${query}`);
    }
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
            type="text"
            name="name"
            value={formData.name}
            onChange={(e) => handleChange("name", e.target.value)}
            minLength={3}
            maxLength={32}
            placeholder={formData.name || "Name"}
        />
        
        <CustomDropdown
            name="rarity"
            options={rarityOptions}
            selectedValues={formData.rarity}
            onChange={handleChange}
            placeholder="Rarity"
            idName = "searchRarity"
        />
        
        <CustomDropdown
            name="type"
            options={typeOptions}
            selectedValues={formData.type}
            onChange={handleChange}
            placeholder="Type"
            idName = "searchTypeI"
        />
        
        <CustomDropdown
            name="needsAdjustment"
            options={configOptions}
            selectedValues={formData.needsAdjustment}
            onChange={handleChange}
            placeholder="Configuration"
            idName = "searchConf"
        />

        <button type="submit" className={Mstyle.searchButton}>Search</button>
        <button type="submit" className={Mstyle.searchButton} onClick={handleReset}>Reset</button>
      </form>
    </div>
  );
}

export default ItemSearchBar;
