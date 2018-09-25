package com.simple.notes.simpleNotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ServletComponentScan
@SpringBootApplication
@ComponentScan("com.simple.notes")
@EnableJpaRepositories("com.simple.notes.repository")
@EntityScan("com.simple.notes.entities")
public class SimpleNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNotesApplication.class, args);
	}
}
