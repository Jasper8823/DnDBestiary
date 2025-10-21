import style from './mainStyle.module.css'
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";


function Header() {
    let pageName;    
    const navigate = useNavigate();
    const location = useLocation();
    const pathParts = location.pathname.split("/").filter(Boolean); 

    const isCalculatorPage = location.pathname === "/combat-calculator";

    const loginRouter = () => {
        navigate(`/login`);
    };

    const signinRouter = () => {
        navigate(`/signup`);
    };

    if (pathParts[1] === "bestiary" || pathParts[0] === "bestiary") {
        pageName = "Bestiary";
    } else if (pathParts[1] === "combat-calculator" || pathParts[0] === "combat-calculator") {
        pageName = "Combat Calculator";
    } else if (pathParts[1] === "items" || pathParts[0] === "items") {
        pageName = "Items";
    } else if (pathParts[1] === "spells" || pathParts[0] === "spells") {
        pageName = "Spells";
    } else if (pathParts[1] === "characters" || pathParts[0] === "characters") {
        pageName = "Characters";
    } else if (pathParts[1] === "create-character" || pathParts[0] === "create-character") {
        pageName = "Create";
    } else {
        pageName = "Error";
    }

    return (
        <div className={style.header}>
        <p className={style.headerText}><b>DnD</b> | {pageName}</p>
            <div className={style.loginDiv}>
                {pathParts.length === 1 && <> <p className={style.link} onClick={loginRouter}>Log in</p> <p className={style.link} onClick={signinRouter}>Sign up</p> </>}
                {pathParts.length === 2 && <> <p className={style.link}>Log out</p> </>}
            </div>
        </div>
    );
}

export default Header;
