import React, { useState } from "react";
import style from "./login.module.css";
import {useNavigate} from "react-router-dom";

export default function Signup() {
    const [form, setForm] = useState({ username: "", email: "", password: "" });
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});
  
    const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });
  
    const validate = () => {
      const err = {};
      if (!form.username.trim()) err.username = "Username required.";
      if (!form.email.trim()) err.email = "Email required.";
      else if (!/\S+@\S+\.\S+/.test(form.email)) err.email = "Invalid email.";
      if (!form.password.trim()) err.password = "Password required.";
      setErrors(err);
      return Object.keys(err).length === 0;
    };
  
    const handleSubmit = async (event) => {
        event.preventDefault();
        if (validate()) {
            const form = event.target;

            const data = {
                username: form.username.value,
                password: form.password.value,
                email: form.email.value
            };
            console.log(data);

            try {;
                const response = await fetch("http://localhost:8080/signup", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(data),
                });
                const rawText = await response.text();
                if(rawText){
                    navigate(`/${rawText}/bestiary`);
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Error while sending request.");
            }
        }
    };
  
    return (
      <div className={style.loginContainer}>
        <div className={style.loginBox}>
          <h1>Sign up</h1>
          <form onSubmit={handleSubmit}>
            {["username", "email", "password"].map(field => (
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
