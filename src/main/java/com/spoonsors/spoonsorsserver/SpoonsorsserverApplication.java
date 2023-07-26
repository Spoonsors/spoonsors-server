package com.spoonsors.spoonsorsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpoonsorsserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpoonsorsserverApplication.class, args);
	}

}
