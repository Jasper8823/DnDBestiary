package com.example.DnDProject.Configurations;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dependency.JavaScript;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebPageOpener implements CommandLineRunner {

    @Override
    public void run(String... args) {
        openWebPage("http://localhost:8080/fillDBMonster");
    }

    private void openWebPage(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that can be called from client-side JavaScript
    @ClientCallable
    public void printName(String name) {
        System.out.println("Hello, " + name);
    }
}
