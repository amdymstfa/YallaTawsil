package com.yallatawsil.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YallatawsilBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(YallatawsilBackendApplication.class, args);
        System.out.println("\n" +
                "====================================================================\n" +
                "   Yalla Tawsil Backend - Delivery Route Optimization System       \n" +
                "====================================================================\n" +
                "   Application démarrée avec succès!                                \n" +
                "   Port: 8080                                                       \n" +
                "   H2 Console: http://localhost:8080/h2-console                     \n" +
                "   Swagger UI: http://localhost:8080/swagger-ui.html                \n" +
                "   API Docs: http://localhost:8080/api-docs                         \n" +
                "====================================================================\n");
	}

}
