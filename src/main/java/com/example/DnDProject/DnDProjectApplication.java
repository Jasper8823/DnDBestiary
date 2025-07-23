package com.example.DnDProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.example.DnDProject")
@EnableCaching
public class DnDProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnDProjectApplication.class, args);

	}

}
