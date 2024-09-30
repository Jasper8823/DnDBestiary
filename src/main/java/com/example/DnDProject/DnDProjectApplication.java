package com.example.DnDProject;

import com.example.DnDProject.Configurations.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.DnDProject")
public class DnDProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DnDProjectApplication.class, args);

		context.getBean(DatabaseConnection.class).Connect();
	}

}
