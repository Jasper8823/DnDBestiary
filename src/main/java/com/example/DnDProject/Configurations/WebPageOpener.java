package com.example.DnDProject.Configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebPageOpener implements CommandLineRunner {

    @Override
    public void run(String... args) {
        openWebPage("http://localhost:8080/fillDBMonster");
    }

    public void openWebPage(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
