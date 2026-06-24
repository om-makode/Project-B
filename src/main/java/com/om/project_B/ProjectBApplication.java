
package com.om.project_B;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ProjectBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBApplication.class, args);
	}
	@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
