import style from './mainStyle.module.css'

function Header(){
    let pageName = "Bestiary";
    return (
        <div className={style.header}>
            <p className={style.headerText}><b>DnD</b> | {pageName}</p>
        </div>
    )
}

export default Header;