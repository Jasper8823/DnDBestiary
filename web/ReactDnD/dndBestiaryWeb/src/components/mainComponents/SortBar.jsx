import style from './mainStyle.module.css'
import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";
import MonsterSearchBar from './Monsters/MonsterSearchBar'
import SpellSearchBar from './Spells/SpellSearchBar'
import ItemSearchBar from './Items/ItemSearchBar'

function SortBar() {
    return (
      <div className={style.searchBar}>
        <Routes>
          <Route path="/bestiary" element={<MonsterSearchBar/>} />
          <Route path="/bestiary/:id" element={<MonsterSearchBar/>} />
          <Route path="/items" element={<ItemSearchBar/>} />
          <Route path="/items/:id" element={<ItemSearchBar/>} />
          <Route path="/spells" element={<SpellSearchBar/>} />
          <Route path="/spells/:id" element={<SpellSearchBar/>} />
        </Routes>
      </div>
    );
}

export default SortBar;