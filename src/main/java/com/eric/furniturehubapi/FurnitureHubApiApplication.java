package com.eric.furniturehubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FurnitureHubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureHubApiApplication.class, args);
		
		System.out.print(" - - - - - - - - - - - Hello from Furniture Hub API - - - - - - - - - -- -  ");
	}

}
