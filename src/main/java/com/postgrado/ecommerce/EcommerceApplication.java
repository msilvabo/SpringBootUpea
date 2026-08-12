package com.postgrado.ecommerce;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Sport");
		category.setDescription("Mochila deportivas");

		Category category1 = new Category();
		category1.setName("Travel");
		category1.setDescription("Mochila Viajes");

		Category categorySaved1 = categoryRepository.save(category);
		Category categorySaved2 = categoryRepository.save(category1);
		System.out.println(categorySaved1.getId().toString());
		System.out.println(categorySaved2.getId().toString());
	}
}
