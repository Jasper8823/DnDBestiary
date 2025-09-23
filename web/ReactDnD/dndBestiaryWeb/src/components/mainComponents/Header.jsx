import style from './mainStyle.module.css'
import { useLocation } from "react-router-dom";

function Header(){
    let pageName;
    const location = useLocation();

    const isCalculatorPage = location.pathname === "/combat-calculator";

    if (location.pathname === "/combat-calculator") {
    pageName = "Combat Calculator";
    } else if (location.pathname.startsWith("/bestiary")) {
    pageName = "Bestiary";
    } else if (location.pathname.startsWith("/items")) {
    pageName = "Items";
    } else if (location.pathname.startsWith("/spells")) {
    pageName = "Spells";
    } else if (location.pathname.startsWith("/characters")) {
    pageName = "Characters";
    } else if (location.pathname.startsWith("/create-character")) {
    pageName = "Create";
    } else {
    pageName = "Error";
    }

    return (
        <div className={style.header}>
            <p className={style.headerText}><b>DnD</b> | {pageName}</p>
        </div>
    )
}

export default Header;