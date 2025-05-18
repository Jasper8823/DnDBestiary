import style from './mainStyle.module.css'
import Monster from './Monsters/Monster'
import MonsterSmall from './Monsters/MonsterSmall'
import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";


function MainComponent() {
    const monsters = [
      { id: 0, name: "Bents", lvl: 1 },
      { id: 1, name: "PCH", lvl: 30 },
      { id: 2, name: "Besik", lvl: 20 },
      { id: 3, name: "Besik", lvl: 20 }
    ];

    return (
      <div className={style.box}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Navigate to="/bestiary" replace />} />
            <Route path="/bestiary" element={<MonsterSmall monsters = {monsters}/>} />
            <Route path="/bestiary/:id" element={<Monster monsters = {monsters}/>} />
          </Routes>
        </BrowserRouter>
      </div>
    )
  }
  
  export default MainComponent