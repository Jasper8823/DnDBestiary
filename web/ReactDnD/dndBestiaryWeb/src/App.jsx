import { BrowserRouter } from "react-router-dom";
import Background from './components/background/Background.jsx'
import MainComponent from './components/mainComponents/MainComponent.jsx';
import Header from './components/mainComponents/Header.jsx';
import SideBar from './components/mainComponents/SideBar.jsx';

function App() {
  return (
    <BrowserRouter>
      <Background/>
      <Header/>
      <SideBar/>
      <MainComponent/>
    </BrowserRouter>
  );
}

export default App;
