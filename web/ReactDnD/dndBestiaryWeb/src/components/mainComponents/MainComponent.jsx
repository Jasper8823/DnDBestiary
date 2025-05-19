import style from './mainStyle.module.css'
import Monster from './Monsters/Monster'
import MonsterSmall from './Monsters/MonsterSmall'
import Item from './Items/Item'
import ItemSmall from './Items/ItemSmall'
import Spell from './Spells/Spell'
import SpellSmall from './Spells/SpellSmall'
import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";


function MainComponent() {
    return (
      <div className={style.box}>
        <Routes>
          <Route path="/" element={<Navigate to="/bestiary" replace />} />
          <Route path="/bestiary" element={<MonsterSmall/>} />
          <Route path="/bestiary/:id" element={<Monster/>} />
          <Route path="/items" element={<ItemSmall/>} />
          <Route path="/items/:id" element={<Item/>} />
          <Route path="/spells" element={<SpellSmall/>} />
          <Route path="/spells/:id" element={<Spell/>} />
        </Routes>
      </div>
    );
}

export default MainComponent