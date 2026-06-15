package com.inventory.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartApplication.class, args);
	}

}
