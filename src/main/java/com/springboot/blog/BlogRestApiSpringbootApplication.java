package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

//for docs
@OpenAPIDefinition(
		info = @Info(
				title = "Springboot Blog Application REST API",
				description = "Documentation for Springboot REST API with CRUD routes, incl. auth with Spring Security",
				version = "v1.0",
				contact = @Contact(
						name = "@jonathanjacka",
						url = "https://info.jonathanjacka.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Github Repository for project",
				url = "https://github.com/jonathanjacka/blog-REST-API-springboot"
		)
)
public class BlogRestApiSpringbootApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiSpringbootApplication.class, args);
	}

}
