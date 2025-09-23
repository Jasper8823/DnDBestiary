import { useState } from 'react';
import style from './spells.module.css';
import Mstyle from '../mainStyle.module.css';
import { useNavigate } from 'react-router-dom';

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

  const handleChange = (e) => {
    const { name, value } = e.target;
    switch (name) {
      case "name":
        setFormData({ ...formData, [name]: value });
        break;
      case "level":
        if (levels.includes(value)) {
            setLevels(levels.filter((s) => s !== value));
        } else {
            setLevels([...levels, value]);
        }
        break;
      case "charClass":
        if (classes.includes(value)) {
            setClasses(classes.filter((s) => s !== value));
        } else {
            setClasses([...classes, value]);
        }
        break;
      case "type":
        if (types.includes(value)) {
            setTypes(types.filter((s) => s !== value));
        } else {
            setTypes([...types, value]);
        }
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
          id={style.searchInput}
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          placeholder="Name"
          minLength={3}
          maxLength={32}
        />

        <select className={Mstyle.searchSelect}  name="level" onChange={handleChange}>
          <option value="" disabled>Level</option>
          {Object.entries(levelOptions).map(([value, label]) => (
            <option key={value} value={value} className={levels.includes(value) ? style.selectedOption : ""}>
              {label}
            </option>
          ))}
        </select>

        <select className={Mstyle.searchSelect}  name="charClass" onChange={handleChange}>
          <option value="" disabled>Class</option>
          {Object.entries(classOptions).map(([value, label]) => (
            <option key={value} value={value} className={classes.includes(value) ? style.selectedOption : ""}>
              {label}
            </option>
          ))}
        </select>

        <select className={Mstyle.searchSelect} id={style.typeSearch} name="type" onChange={handleChange}>
          <option value="" disabled>Type</option>
          {Object.entries(typeOptions).map(([value, label]) => (
            <option key={value} value={value} className={types.includes(value) ? style.selectedOption : ""}>
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

export default SpellSearchBar;
