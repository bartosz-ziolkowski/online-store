package com.bazi.online_store;

import com.bazi.online_store.models.Brand;
import com.bazi.online_store.models.Category;
import com.bazi.online_store.models.Product;
import com.bazi.online_store.repositories.BrandRepository;
import com.bazi.online_store.repositories.CategoryRepository;
import com.bazi.online_store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class OnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Bean
	public CommandLineRunner seedDatabase(BrandRepository brandRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args -> {
			Brand veja = new Brand("Veja");
			Brand adidas = new Brand("Adidas");
			Brand nike = new Brand("Nike");
			//brandRepository.saveAll(Arrays.asList(veja, adidas, nike));

			Category running = new Category("Running");
			Category outdoor = new Category("Outdoor");
			Category city = new Category("City");
			//categoryRepository.saveAll(Arrays.asList(running, outdoor, city));

			String[] imageUrls = {
					"shoes-1.png", "shoes-2.png", "shoes-3.png", "shoes-4.png", "shoes-5.png",
					"shoes-6.png", "shoes-7.png", "shoes-8.png", "shoes-9.png", "shoes-10.png",
					"shoes-11.png", "shoes-12.png", "shoes-13.png", "shoes-14.png", "shoes-15.png",
					"shoes-16.png", "shoes-17.png", "shoes-18.png"
			};

			Random random = new Random();

			Product[] products = new Product[]{
					new Product(running, veja, "SKU001", "Veja Running Shoes", "High-performance running shoes", 150.00, getRandomImageUrl(imageUrls, random), true, 20, new Date(), new Date()),
					new Product(running, adidas, "SKU002", "Adidas Ultraboost", "Comfortable running shoes with Boost technology", 180.00, getRandomImageUrl(imageUrls, random), true, 30, new Date(), new Date()),
					new Product(running, nike, "SKU003", "Nike Air Zoom", "Lightweight running shoes", 170.00, getRandomImageUrl(imageUrls, random), true, 25, new Date(), new Date()),
					new Product(outdoor, veja, "SKU004", "Veja Hiking Boots", "Durable boots for outdoor adventures", 200.00, getRandomImageUrl(imageUrls, random), true, 15, new Date(), new Date()),
					new Product(outdoor, adidas, "SKU005", "Adidas Terrex", "Reliable outdoor shoes", 190.00, getRandomImageUrl(imageUrls, random), true, 10, new Date(), new Date()),
					new Product(outdoor, nike, "SKU006", "Nike ACG", "All-conditions gear for outdoor activities", 210.00, getRandomImageUrl(imageUrls, random), true, 12, new Date(), new Date()),
					new Product(city, veja, "SKU007", "Veja Urban Sneakers", "Stylish sneakers for city wear", 130.00, getRandomImageUrl(imageUrls, random), true, 50, new Date(), new Date()),
					new Product(city, adidas, "SKU008", "Adidas City Shoes", "Comfortable and stylish city shoes", 140.00, getRandomImageUrl(imageUrls, random), true, 40, new Date(), new Date()),
					new Product(city, nike, "SKU009", "Nike Casual", "Everyday casual shoes", 160.00, getRandomImageUrl(imageUrls, random), true, 35, new Date(), new Date())
			};

			//productRepository.saveAll(Arrays.asList(products));
		};
	}

	private String getRandomImageUrl(String[] imageUrls, Random random) {
		return imageUrls[random.nextInt(imageUrls.length)];
	}
}
