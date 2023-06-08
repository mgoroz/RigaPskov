package com.example.RigaPskov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RigaPskovApplication {

	public static void main(String[] args) {
		SpringApplication.run(RigaPskovApplication.class, args);
	}

}
