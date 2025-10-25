import style from './mainStyle.module.css'
import Monster from './Monsters/Monster'
import MonsterSmall from './Monsters/MonsterSmall'
import Item from './Items/Item'
import ItemSmall from './Items/ItemSmall'
import Spell from './Spells/Spell'
import SpellSmall from './Spells/SpellSmall'
import CombatCalculator from './CombatCalculator/CombatCalculator'
import Characters from './Characters/Characters'
import CreateCharacter from './Characters/CreateCharacter'
import CreateCharacterSpells from './Characters/CreateCharacterSpells'
import Login from './Login/Login'
import Signup from './Login/Signup'
import Character from './Characters/Character'
import { useLocation } from "react-router-dom";
import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";


function MainComponent() {
    const location = useLocation();

    const toolBoxRoutes = [
      "/combat-calculator",
      "/characters",
      "/create-character"
    ];

    const logBoxRoutes = [
      "/login",
      "/signup"
    ];

    const isToolBoxPage =
      toolBoxRoutes.includes(location.pathname) ||
      /^\/create-character\/[^/]+$/.test(location.pathname)||
      /^\/characters\/[^/]+$/.test(location.pathname);

    const isLoginBoxPage =
      logBoxRoutes.includes(location.pathname)

    return (
      <div className={style.box} id={isToolBoxPage ? style.toolBox : isLoginBoxPage ? style.loginBox :  style.bestiaryBox}>
        <Routes>
          <Route path="/" element={<Navigate to="/bestiary" replace />} />
          <Route path="/bestiary" element={<MonsterSmall/>} />
          <Route path="/bestiary/:id" element={<Monster/>} />
          <Route path="/items" element={<ItemSmall/>} />
          <Route path="/items/:id" element={<Item/>} />
          <Route path="/spells" element={<SpellSmall />} /> 
          <Route path="/spells/:id" element={<Spell/>} />
          <Route path="/combat-calculator" element={<CombatCalculator/>} />
          <Route path="/:userid/bestiary" element={<MonsterSmall/>} />
          <Route path="/:userid/bestiary/:id" element={<Monster/>} />
          <Route path="/:userid/items" element={<ItemSmall/>} />
          <Route path="/:userid/items/:id" element={<Item/>} />
          <Route path="/:userid/spells" element={<SpellSmall />} /> 
          <Route path="/:userid/spells/:id" element={<Spell/>} />
          <Route path="/:userid/combat-calculator" element={<CombatCalculator/>} />
          <Route path="/:userid/characters" element={<Characters/>} />
          <Route path="/:userid/characters/:id" element={<Character/>} />
          <Route path="/:userid/create-character" element={<CreateCharacter/>} />
          <Route path="/:userid/create-character/:uuid" element={<CreateCharacterSpells/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/signup" element={<Signup/>} />
        </Routes>
      </div>
    );
}

export default MainComponent