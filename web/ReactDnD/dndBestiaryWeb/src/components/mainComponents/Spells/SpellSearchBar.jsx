import { useState } from 'react';
import style from './spells.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';
import CustomDropdown from '../CustomDropdown.jsx';

const levelOptions = {
    0: "Plot",
    1: "1 level",
    2: "2 level",
    3: "3 level",
    4: "4 level",
    5: "5 level",
    6: "6 level",
    7: "7 level",
    8: "8 level",
    9: "9 level"
};

const classOptions = {
    bard: "Bard",
    cleric: "Cleric",
    druid: "Druid",
    sorcerer: "Sorcerer",
    warlock: "Warlock",
    wizard: "Wizard"
};

const typeOptions = {
    abjuration: "Abjuration",
    conjuration: "Conjuration",
    divination: "Divination",
    enchantment: "Enchantment",
    evocation: "Evocation",
    illusion: "Illusion",
    necromancy: "Necromancy",
    transmutation: "Transmutation"
};

function SpellSearchBar() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: '',
    level: [],
    charClass: [],
    type: []
  });

  const [levels, setLevels] = useState([]);
  const [classes, setClasses] = useState([]);
  const [types, setTypes] = useState([]);

  const handleChange = (name, values) => {
    switch (name) {
      case "name":
        setFormData({ ...formData, [name]: values });
        break;
      case "level":
        setLevels(values);
        setFormData({
            ...formData,
            level: values,
        });
        break;
      case "charClass":
        setClasses(values);
        setFormData({
            ...formData,
            charClass: values,
        });
        break;
      case "type":
        setTypes(values);
        setFormData({
            ...formData,
            type: values,
        });
        break;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormData({
      ...formData,
      level: levels,
      charClass: classes,
      type: types
    });
    const query = new URLSearchParams(formData).toString();
    navigate(`/spells?${query}`);
  };

  const handleReset = () => {
    setFormData({ name: '', level: [], charClass: [], type: [] });
    setLevels([]);
    setClasses([]);
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
            name="level"
            options={levelOptions}
            selectedValues={formData.level}
            onChange={handleChange}
            placeholder="Level"
            idName = "searchLevel"
        />
        
        <CustomDropdown
            name="charClass"
            options={classOptions}
            selectedValues={formData.charClass}
            onChange={handleChange}
            placeholder="Class"
            idName = "searchClass"
        />
        
        <CustomDropdown
            name="type"
            options={typeOptions}
            selectedValues={formData.type}
            onChange={handleChange}
            placeholder="Type"
            idName = "searchTypeS"
        />

        <button type="submit" className={Mstyle.searchButton}>Search</button>
        <button type="submit" className={Mstyle.searchButton} onClick={handleReset}>Reset</button>
      </form>
    </div>
  );
}

export default SpellSearchBar;
