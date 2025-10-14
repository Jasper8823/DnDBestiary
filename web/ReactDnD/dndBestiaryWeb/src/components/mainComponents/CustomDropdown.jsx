import { useState, useRef, useEffect } from "react";
import style from "./mainStyle.module.css";

function CustomDropdown({ name, options, selectedValues, onChange, placeholder, idName }) {
    const [open, setOpen] = useState(false);
    const dropdownRef = useRef(null);

    const toggleOption = (value) => {
        if (selectedValues.includes(value)) {
            onChange(name, selectedValues.filter((v) => v !== value));
        } else {
            onChange(name, [...selectedValues, value]);
        }
    };

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setOpen(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    // Sort options so "plot" is always first
    const sortedOptions = Object.entries(options).sort(([a], [b]) => {
        if (a === "plot") return -1;
        if (b === "plot") return 1;
        return 0;
    });

    return (
        <div
            ref={dropdownRef}
            className={`${style.searchSelect} ${style.dropdown}`}
            id={`${style[idName]}`}
        >
            <div className={style.dropdownHeader} onClick={() => setOpen(!open)}>
                {placeholder}
                <span className={style.arrow}>{open ? "▲" : "▼"}</span>
            </div>

            {open && (
                <div className={style.dropdownList}>
                    {sortedOptions.map(([value, label]) => (
                        <div
                            key={value}
                            className={`${style.dropdownOption} ${
                                selectedValues.includes(value) ? style.selectedOption : ""
                            }`}
                            onClick={() => toggleOption(value)}
                        >
                            {label}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default CustomDropdown;
