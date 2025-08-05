import { useNavigate, useParams } from "react-router-dom";
import style from './character.module.css';
import Mstyle from '../mainStyle.module.css';
import { useEffect, useState } from 'react';

function CreateCharacterSpells() {
    const {uuid} = useParams();

    const navigate = useNavigate();
    const [neededInfo, setNeededInfo] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/create-character?uuid=${uuid}`)
            .then(response => response.json())
            .then(data => setNeededInfo(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return <div><p>I work</p></div>;
}

export default CreateCharacterSpells;