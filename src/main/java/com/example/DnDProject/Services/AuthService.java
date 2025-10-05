package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Login.Login;
import com.example.DnDProject.Entities.Login.User;
import com.example.DnDProject.Repositories.Login.LoginRepository;
import com.example.DnDProject.Repositories.Login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class AuthService {

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Login login(String email, String password) {
        Optional<Login> loginOpt = loginRepo.findByEmail(email);
        if (loginOpt.isPresent()) {
            Login login = loginOpt.get();
            if (passwordEncoder.matches(password, login.getPassword())) {
                return login;
            }
        }
        return null;
    }

    public Login signup(String email, String password, String username) {
        if (loginRepo.existsById(email)) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(passwordEncoder.encode(password));
        login.setUser(user);

        return loginRepo.save(login);
    }
}
