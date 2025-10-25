package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.Login.LoginDTO;
import com.example.DnDProject.DTOs.Login.SignUpDTO;
import com.example.DnDProject.Entities.Login.Login;
import com.example.DnDProject.Services.AuthService;
import com.example.DnDProject.Services.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionManager sessionManager;

    private static final long TIMEOUT_SECONDS=1800;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        Login login = authService.login(dto.getEmail(), dto.getPassword());
        if (login != null) {
            System.out.println("wqdas");
            return sessionManager.createSession(TIMEOUT_SECONDS);
        }
        System.out.println("asdaasddad");
        return "1";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpDTO dto) {
        authService.signup(dto.getEmail(), dto.getPassword(), dto.getUsername());
        return sessionManager.createSession(TIMEOUT_SECONDS);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String sessionId) {
        sessionManager.removeSession(sessionId);
        return "Logged out";
    }

    @GetMapping("/current")
    public String currentUser(@RequestParam String sessionId) {
        return sessionManager.isValid(sessionId) ? "LoggedIn" : "Guest";
    }

    @PostMapping("/prolong")
    @ResponseBody
    public String prolongSession(@RequestParam("userid") String sessionId) {
        System.out.println("wkjhebfkwjefwfew");
        return sessionManager.prolong(sessionId, TIMEOUT_SECONDS);
    }
}