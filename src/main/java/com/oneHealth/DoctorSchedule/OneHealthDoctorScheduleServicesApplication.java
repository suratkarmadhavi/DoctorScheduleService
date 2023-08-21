package com.oneHealth.DoctorSchedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main class to start the OneHealth Doctor Schedule Services application.
 *
 * This class serves as the entry point for the Doctor Schedule Service application.
 * It uses the SpringApplication class to bootstrap and launch the Spring Boot application.
 * 
 * Note: Make sure to import the required Spring Boot annotations.
 * 
 * @author Madhavi
 * @version 1.0
 */
@RestController
@SpringBootApplication
public class OneHealthDoctorScheduleServicesApplication {

    public static void main(String[] args) {
        // Use SpringApplication.run() to start the Spring Boot application and run the Doctor Schedule Service.
        SpringApplication.run(OneHealthDoctorScheduleServicesApplication.class, args);
    }
    @GetMapping
	public String Welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-DoctorFeesService)!!!";
	}

}
