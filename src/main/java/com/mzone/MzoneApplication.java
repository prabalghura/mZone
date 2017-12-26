package com.mzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.mzone")
public class MzoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MzoneApplication.class, args);
	}
}
