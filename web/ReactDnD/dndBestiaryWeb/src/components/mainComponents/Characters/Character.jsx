import { useParams } from 'react-router-dom';
import style from './character.module.css';
import { useEffect, useState } from 'react';
import StatBox from '../Monsters/StatBox';
import SpellSlotBox from './SpellSlotBox';

function Character() {
    const { id } = useParams();
    const [character, setCharacter] = useState(null);

    const [allItems] = useState([
        { id: 1, name: "Longsword" },
        { id: 2, name: "Shortbow" },
        { id: 3, name: "Leather Armor" },
        { id: 4, name: "Chainmail" },
        { id: 5, name: "Health Potion" },
        { id: 6, name: "Mana Potion" },
        { id: 7, name: "Dagger" },
        { id: 8, name: "Great Axe" },
        { id: 9, name: "Magic Wand" },
        { id: 10, name: "Shield" },
        { id: 11, name: "Torch" },
        { id: 12, name: "Rope (50ft)" },
        { id: 13, name: "Spellbook" },
        { id: 14, name: "Herbs Pouch" },
        { id: 15, name: "Warhammer" },
        { id: 16, name: "Crossbow" },
        { id: 17, name: "Arrows (20)" },
        { id: 18, name: "Magic Ring" },
        { id: 19, name: "Scroll of Fireball" },
        { id: 20, name: "Boots of Speed" },
    ]);

    const [characterItems, setCharacterItems] = useState([]); 
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [search, setSearch] = useState("");

    useEffect(() => {
        fetch(`http://localhost:8080/getCharacter?id=${id}`)
            .then(response => response.json())
            .then(data => setCharacter(data))
            .catch(error => console.error('Error fetching data:', error));
    }, [id]);

    if (!character) return <p>Loading character...</p>;

    const availableItems = allItems.filter(
        item =>
            !characterItems.some(ci => ci.id === item.id) &&
            item.name.toLowerCase().includes(search.toLowerCase())
    );

    const addItemToCharacter = (item) => {
        setCharacterItems([...characterItems, item]);
        setDropdownOpen(false);
        setSearch("");
    };

    const removeItemFromCharacter = (itemId) => {
        setCharacterItems(characterItems.filter(item => item.id !== itemId));
    };

    return (
        <div className={style.mainBox}>
            <p id={style.charName}><b>{character.name}</b>{" ("+character.level+") " + character.backstory}</p>
            
            <StatBox name = {"Str"} value = {character.stats["strength"]} isGrey = {true}/>
            <StatBox name = {"Dxt"} value = {character.stats["dexterity"]} isGrey = {false}/>
            <StatBox name = {"Con"} value = {character.stats["constitution"]} isGrey = {true}/>
            <StatBox name = {"Int"} value = {character.stats["intelligence"]} isGrey = {false}/>
            <StatBox name = {"Wis"} value = {character.stats["wisdom"]} isGrey = {true}/>
            <StatBox name = {"Chr"} value = {character.stats["charisma"]} isGrey = {false}/>
            
            {character.race && <p><b>Race:</b> {character.race}</p>}
            {character.class && <p><b>Class:</b> {character.class}</p>}
            
            {character.slots && character.slots["spell_num"] && <p>Number of spells: {character.slots["spell_num"]}</p>}
            {character.slots && character.slots["plot_num"] && <p>Number of plots: {character.slots["plot_num"]}</p>}
            {character.slots && 
                <><SpellSlotBox name = {"I"} value = {character.slots["lvl1"]} isGrey = {true}/>
                <SpellSlotBox name = {"II"} value = {character.slots["lvl2"]} isGrey = {false}/>
                <SpellSlotBox name = {"III"} value = {character.slots["lvl3"]} isGrey = {true}/>
                <SpellSlotBox name = {"IV"} value = {character.slots["lvl4"]} isGrey = {false}/>
                <SpellSlotBox name = {"V"} value = {character.slots["lvl5"]} isGrey = {true}/>
                <SpellSlotBox name = {"VI"} value = {character.slots["lvl6"]} isGrey = {false}/>
                <SpellSlotBox name = {"VII"} value = {character.slots["lvl7"]} isGrey = {true}/>
                <SpellSlotBox name = {"VIII"} value = {character.slots["lvl8"]} isGrey = {false}/>
                <SpellSlotBox name = {"IX"} value = {character.slots["lvl9"]} isGrey = {true}/></>
            }

            {character.spells && character.spells.length > 0 && (
                <>
                    <h3>Spells</h3>
                    {Array.from({ length: 10 }, (_, lvl) => (
                    <div key={lvl}>
                        <ul>
                        {character.spells
                            .filter(spell => spell.level === lvl)
                            .map((spell, index) => (
                            <li key={index}>{spell.level+" "+spell.name}</li>
                            ))}
                        </ul>
                    </div>
                    ))}
                </>
            )}
            <div className={style.itemsSection}>
            <h3>Items</h3>
            <ul>
                {characterItems.map((item) => (
                <li key={item.id} className={style.itemRow}>
                    <span>{item.name}</span>
                    <button
                    className={style.removeButton}
                    onClick={() => removeItemFromCharacter(item.id)}
                    >
                    ❌
                    </button>
                </li>
                ))}
            </ul>

            <div>
                {dropdownOpen ? (
                <div className={style.dropdownBox}>
                    <input
                    type="text"
                    placeholder="Search items..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className={style.searchInput}
                    />
                    <div className={style.dropdownList}>
                    {availableItems.map(item => (
                        <div
                        key={item.id}
                        className={style.dropdownOption}
                        onClick={() => addItemToCharacter(item)}
                        >
                        {item.name}
                        </div>
                    ))}
                    {availableItems.length === 0 && <p>No items found</p>}
                    </div>
                </div>
                ) : (
                <button className={style.addButton} onClick={() => setDropdownOpen(true)}>
                    +
                </button>
                )}
            </div>
            </div>
        </div>
    );
}

export default Character;
