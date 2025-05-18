import style from './mainStyle.module.css'
import Monster from './Monsters/Monster'
import MonsterSmall from './Monsters/MonsterSmall'
import Item from './Items/Item'
import ItemSmall from './Items/ItemSmall'
import Spell from './Spells/Spell'
import SpellSmall from './Spells/SpellSmall'
import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";


function MainComponent() {
    const monsters = [
        { id: 0, name: "Light", lvl: 0 },
        { id: 1, name: "Wall of mud", lvl: 1 },
        { id: 2, name: "Thunder wave", lvl: 1 },
        { id: 3, name: "Fire ball", lvl: 3 }
    ];

    const spells = [
      { id: 0, name: "Light", lvl: 0 },
      { id: 1, name: "Wall of mud", lvl: 1 },
      { id: 2, name: "Thunder wave", lvl: 1 },
      { id: 3, name: "Fire ball", lvl: 3 }
    ];

    const items = [
      { id: 0, name: "Axe", type: "W"},
      { id: 1, name: "Log sword", type: "W" },
      { id: 2, name: "Healing potion", type: "P" },
      { id: 3, name: "Chestplate", type: "A" }
    ];

    return (
      <div className={style.box}>
        <Routes>
          <Route path="/" element={<Navigate to="/bestiary" replace />} />
          <Route path="/bestiary" element={<MonsterSmall/>} />
          <Route path="/bestiary/:id" element={<Monster monsters={monsters} />} />
          {/* <Route path="/bestiary" element={<MonsterSmall/>} />
          <Route path="/bestiary/:id" element={<Monster/>} /> */}
          <Route path="/items" element={<ItemSmall items={items} />} />
          <Route path="/items/:id" element={<Item items={items} />} />
          <Route path="/spells" element={<SpellSmall spells={spells} />} />
          <Route path="/spells/:id" element={<Spell spells={spells} />} />
        </Routes>
      </div>
    );
}

export default MainComponent