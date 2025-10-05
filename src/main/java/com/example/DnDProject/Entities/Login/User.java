package com.example.DnDProject.Entities.Login;

import com.example.DnDProject.Entities.Character.Character;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue()
    private int id;

    private String username;

    @OneToOne(mappedBy = "user")
    private Login login;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
