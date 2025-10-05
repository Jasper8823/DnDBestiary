package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.Login.LoginDTO;
import com.example.DnDProject.DTOs.Login.SignUpDTO;
import com.example.DnDProject.Entities.Login.Login;
import com.example.DnDProject.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody LoginDTO dto, HttpSession session) {
        Login login = authService.login(dto.getEmail(), dto.getPassword());
        if (login != null) {
            session.setAttribute("user", login.getUser());
            return true;
        }
        return false;
    }

    @PostMapping("/signup")
    @ResponseBody
    public void signup(@RequestBody SignUpDTO dto, HttpSession session) {
        Login login = authService.signup(dto.getEmail(), dto.getPassword(), dto.getUsername());
        session.setAttribute("user", login.getUser());
    }

    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }

    @GetMapping("/current")
    @ResponseBody
    public Object currentUser(HttpSession session) {
        Object user = session.getAttribute("user");
        return (user != null) ? user : "Guest";
    }
}
