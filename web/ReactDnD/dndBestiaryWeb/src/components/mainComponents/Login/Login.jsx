import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import style from "./login.module.css";

export default function Login() {
    const [form, setForm] = useState({email: "", password: "" });

    const [errors, setErrors] = useState({});

    const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

    const validate = () => {
        const err = {};
        if (!form.email.trim()) err.email = "Email required.";
        else if (!/\S+@\S+\.\S+/.test(form.email)) err.email = "Invalid email.";
        if (!form.password.trim()) err.password = "Password required.";
        setErrors(err);
        return Object.keys(err).length === 0;
    };

    const handleSubmit = async e => {
        e.preventDefault();
        if (validate()) {
            const data = {
                password: form.password.value,
                email: form.email.value
            };

            try {
                console.log(data);
                const response = await fetch("http://localhost:8080/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(data),
                });
                // const rawText = await response.text();
                // if(rawText){
                //     navigate(`/create-character/${rawText}`);
                // }
            } catch (error) {
                console.error("Error:", error);
                alert("Error while sending request.");
            }
        }
    };

    return (
        <div className={style.loginContainer}>
        <div className={style.loginBox} initial={{ opacity: 0, y: -20 }} animate={{ opacity: 1, y: 0 }} transition={{ duration: 0.8 }}>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
            {["email", "password"].map(field => (
                <div key={field} className={style.formGroup}>
                <label>{field.charAt(0).toUpperCase() + field.slice(1)}</label>
                <input type={field === "password" ? "password" : field} name={field} value={form[field]} onChange={handleChange} placeholder={`Enter your ${field}`} />
                    {errors[field] && (
                    <p className={style.error}>
                        {errors[field]}
                    </p>
                    )}
                </div>
            ))}
            <button type="submit">Submit</button>
            </form>
        </div>
        </div>
    );
}

