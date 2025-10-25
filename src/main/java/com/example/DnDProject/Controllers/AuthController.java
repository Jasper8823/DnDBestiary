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
            return sessionManager.createSession(login.getUser().getId(), TIMEOUT_SECONDS);
        }
        return "1";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpDTO dto) {
        Login login = authService.signup(dto.getEmail(), dto.getPassword(), dto.getUsername());
        return sessionManager.createSession(login.getUser().getId(), TIMEOUT_SECONDS);
    }


    @GetMapping("/current")
    public String currentUser(@RequestParam String sessionId) {
        int userId = sessionManager.getUserId(sessionId);
        return userId != -1 ? "LoggedIn: " + userId : "Guest";
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String sessionId) {
        sessionManager.removeSession(sessionId);
        return "Logged out";
    }

    @PostMapping("/prolong")
    @ResponseBody
    public String prolongSession(@RequestParam("userid") String sessionId) {
        return sessionManager.prolong(sessionId, TIMEOUT_SECONDS);
    }
}