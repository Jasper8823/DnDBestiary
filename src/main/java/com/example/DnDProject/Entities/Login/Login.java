package com.example.DnDProject.Entities.Login;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "login")
public class Login {
    @Id
    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SELECT)
    private User user;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
