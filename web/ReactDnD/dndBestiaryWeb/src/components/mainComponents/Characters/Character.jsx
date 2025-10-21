import { useParams } from 'react-router-dom';
import style from './character.module.css';
import { useEffect, useState } from 'react';
import StatBox from '../Monsters/StatBox';
import SpellSlotBox from './SpellSlotBox';

function Character() {
    const { id } = useParams();
    const [character, setCharacter] = useState(null);


    const [characterItems, setCharacterItems] = useState([]); 
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [search, setSearch] = useState("");
    const [allItems, setItems] = useState([]);

    useEffect(() => {
    fetch(`http://localhost:8080/getCharacter?id=${id}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setCharacter(data);
            if (data.allItems) {
                setItems(data.allItems);
            }
            if (data.items) {
                setCharacterItems(data.items);
            }
            })
        .catch(error => console.error('Error fetching data:', error));
    }, [id]);

    if (!character) return <p>Loading character...</p>;
    
    const availableItems = allItems.filter(
        item =>
            !characterItems.some(ci => ci.id === item.id) &&
            item.name.toLowerCase().includes(search.toLowerCase())
    );

    const addItem = async (itemId) => {
        const data = {
            id :  id,
            itemId : itemId
        };

        try {
            console.log(data);
            const response = await fetch("http://localhost:8080/character-item-add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
        } catch (error) {
            console.error("Error:", error);
            alert("Error while sending request.");
        }
    };

    const removeItem = async (itemId) => {
        const data = {
            id :  id,
            itemId : itemId
        };
        
        try {
            console.log(data);
            const response = await fetch("http://localhost:8080/character-item-remove", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
        } catch (error) {
            console.error("Error:", error);
            alert("Error while sending request.");
        }
    };

    const addItemToCharacter = (item) => {
        setCharacterItems([...characterItems, item]);
        setDropdownOpen(false);
        setSearch("");
        addItem(item.name);
    };

    const removeItemFromCharacter = (itemId) => {
        setCharacterItems(characterItems.filter(item => item.name !== itemId));
        removeItem(itemId);
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
            
            {/* {character.slots && character.slots["spell_num"] && <p>Number of spells: {character.slots["spell_num"]}</p>}
            {character.slots && character.slots["plot_num"] && <p>Number of plots: {character.slots["plot_num"]}</p>} */}
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
                    onClick={() => removeItemFromCharacter(item.name)}
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
                    {allItems.map(item => (
                        <div
                        key={item.name}
                        className={style.dropdownOption}
                        onClick={() => addItemToCharacter(item)}
                        >
                        {item.name}
                        </div>
                    ))}
                    {allItems.length === 0 && <p>No items found</p>}
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
