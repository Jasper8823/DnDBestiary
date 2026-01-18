import { useState, useRef, useEffect } from "react";
import style from "./mainStyle.module.css";

function SingleDropdown({ name, options, selectedValue, onChange, placeholder, idName, autoAdd = false }) {
    const [open, setOpen] = useState(false);
    const [search, setSearch] = useState("");
    const dropdownRef = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setOpen(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    const handleSelect = (value) => {
        onChange(name, value);
        setSearch("");
        if (autoAdd) setOpen(false);
        else setOpen(false);
    };

    const filteredOptions = Object.entries(options).filter(([_, label]) =>
        label.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div
            ref={dropdownRef}
            className={${style.searchSelectS} ${style.dropdown}}
            id={${style[idName]}}
        >
            <div className={style.dropdownHeader} onClick={() => setOpen(!open)}>
                {selectedValue
                    ? options[selectedValue]
                    : placeholder}
                <span className={style.arrow}>{open ? "▲" : "▼"}</span>
            </div>

            {open && (
                <div className={style.dropdownList}>
                    <input
                        type="text"
                        placeholder="Search..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        className={style.searchInputS}
                    />
                    {filteredOptions.length > 0 ? (
                        filteredOptions.map(([value, label]) => (
                            <div
                                key={value}
                                className={`${style.dropdownOption} ${
                                    selectedValue === value ? style.selectedOption : ""
                                }`}
                                onClick={() => handleSelect(value)}
                            >
                                {label}
                            </div>
                        ))
                    ) : (
                        <div className={style.dropdownOption}>No results</div>
                    )}
                </div>
            )}
        </div>
    );
}

export default SingleDropdown;