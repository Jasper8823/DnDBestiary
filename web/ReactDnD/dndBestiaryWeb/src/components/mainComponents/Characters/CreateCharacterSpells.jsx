import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";

function CreateCharacterSpells() {
    const { uuid } = useParams();
    const navigate = useNavigate();

    const [neededInfo, setNeededInfo] = useState(null);
    const [selectedSpells, setSelectedSpells] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/create-character?uuid=${uuid}`)
            .then(response => response.json())
            .then(data => {
                if(data.stat_raise===-1){
                    navigate(`/characters`);
                }
                setNeededInfo(data);
                setSelectedSpells(Array(data.spells_num).fill(""));
            })
            .catch(error => console.error("Error fetching data:", error));
    }, [uuid]);

    if (!neededInfo) {
        return <div><p>Loading</p></div>;
    }

    const spellsList = Object.keys(neededInfo.spells);

    const handleSelectChange = (index, value) => {
        setSelectedSpells(prev => {
            const newSelection = [...prev];
            newSelection[index] = value;
            return newSelection;
        });
    };

    const getAvailableSpells = (index) => {
        let chosenSpells = Array.from(selectedSpells);

        chosenSpells[index] = "";

        return spellsList.filter(spell => !chosenSpells.includes(spell));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Selected spells:", selectedSpells);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                {selectedSpells.map((selected, index) => (
                    <div key={index}>
                        <select
                            required
                            value={selected}
                            onChange={(e) => handleSelectChange(index, e.target.value)}
                        >
                            <option value="">-- Select a spell --</option>
                            {getAvailableSpells(index).map(spell => (
                                <option key={spell} value={spell}>
                                    {neededInfo.spells[spell]+" "+spell}
                                </option>
                            ))}
                        </select>
                    </div>
                ))}
                <button type="submit">Save Spells</button>
            </form>
        </div>
    );
}

export default CreateCharacterSpells;