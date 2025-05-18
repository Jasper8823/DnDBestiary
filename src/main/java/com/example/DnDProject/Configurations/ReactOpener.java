package com.example.DnDProject.Configurations;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ReactOpener implements ApplicationRunner {

    private Process reactProcess;

    @Override
    public void run(ApplicationArguments args) {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "npm run dev");
        pb.directory(new File("../DnDBestiary/web/ReactDnD/dndBestiaryWeb/"));
        pb.inheritIO();

        try {
            reactProcess = pb.start();
            System.out.println("React development server started.");
        } catch (IOException e) {
            System.err.println("Failed to start React app: " + e.getMessage());
        }
    }




}
