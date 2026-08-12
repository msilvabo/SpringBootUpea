package com.postgrado.ecommerce;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
