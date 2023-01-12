package com.example.ordenadores;

import com.example.ordenadores.Entities.Laptop;
import com.example.ordenadores.Repository.LaptopRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdenadoresApplication {

	public static void main(String[] args) {
		//SpringApplication.run(OrdenadoresApplication.class, args);

		ApplicationContext context = SpringApplication.run(OrdenadoresApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);
		repository.save(new Laptop(null, "ajajaja", "jsdnfkjs"));
	}

}
