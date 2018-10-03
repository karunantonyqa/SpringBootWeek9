package com.qa.mySpringBootDatabase;

import org.springframework.boot.SpringApplication;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableJpaAuditing
public class MySpringBootDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootDatabaseApplication.class, args);
	}
}
