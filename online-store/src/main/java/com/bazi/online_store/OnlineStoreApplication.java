package com.bazi.online_store;

import com.bazi.online_store.models.Brand;
import com.bazi.online_store.models.Category;
import com.bazi.online_store.models.Product;
import com.bazi.online_store.models.order_aggregate.DeliveryMethod;
import com.bazi.online_store.repositories.BrandRepository;
import com.bazi.online_store.repositories.CategoryRepository;
import com.bazi.online_store.repositories.DeliveryMethodRepository;
import com.bazi.online_store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
	public CommandLineRunner seedDatabase(BrandRepository brandRepository, CategoryRepository categoryRepository, ProductRepository productRepository, DeliveryMethodRepository deliveryMethodRepository) {
		return args -> {
			List<Brand> brands = Arrays.asList(
					new Brand("Veja"),
					new Brand("Adidas"),
					new Brand("Nike"),
					new Brand("Puma"),
					new Brand("Reebok"),
					new Brand("New Balance"),
					new Brand("Asics"),
					new Brand("Under Armour"),
					new Brand("Saucony"),
					new Brand("Mizuno")
			);


			List<Category> categories = Arrays.asList(
					new Category("Running"),
					new Category("Outdoor"),
					new Category("City"),
					new Category("Training"),
					new Category("Casual"),
					new Category("Basketball"),
					new Category("Soccer"),
					new Category("Tennis"),
					new Category("Golf"),
					new Category("Skateboarding")
			);


			List<DeliveryMethod> deliveryMethods = Arrays.asList(
					new DeliveryMethod("No shipping", "0 days", "No shipping", 0),
					new DeliveryMethod("Standard", "4 days", "Standard shipping", 20),
					new DeliveryMethod("Premium", "2 days", "Premium shipping", 40),
					new DeliveryMethod("Fast", "1 day", "Fast shipping", 60)
			);


			String[] imageUrls = {
					"shoes-1.png", "shoes-2.png", "shoes-3.png", "shoes-4.png", "shoes-5.png",
					"shoes-6.png", "shoes-7.png", "shoes-8.png", "shoes-9.png", "shoes-10.png",
					"shoes-11.png", "shoes-12.png", "shoes-13.png", "shoes-14.png", "shoes-15.png",
					"shoes-16.png", "shoes-17.png", "shoes-18.png"
			};

			Random random = new Random();


			List<Product> products = Arrays.asList(
					new Product(categories.get(0), brands.get(0), "SKU001", "Veja Running Shoes", "High-performance running shoes", 150.00, getRandomImageUrl(imageUrls, random), true, 20, new Date(), new Date()),
					new Product(categories.get(0), brands.get(1), "SKU002", "Adidas Ultraboost", "Comfortable running shoes with Boost technology", 180.00, getRandomImageUrl(imageUrls, random), true, 30, new Date(), new Date()),
					new Product(categories.get(0), brands.get(2), "SKU003", "Nike Air Zoom", "Lightweight running shoes", 170.00, getRandomImageUrl(imageUrls, random), true, 25, new Date(), new Date()),
					new Product(categories.get(1), brands.get(0), "SKU004", "Veja Hiking Boots", "Durable boots for outdoor adventures", 200.00, getRandomImageUrl(imageUrls, random), true, 15, new Date(), new Date()),
					new Product(categories.get(1), brands.get(3), "SKU005", "Puma Outdoor", "Reliable outdoor shoes", 190.00, getRandomImageUrl(imageUrls, random), true, 10, new Date(), new Date()),
					new Product(categories.get(1), brands.get(4), "SKU006", "Reebok Trail", "All-conditions gear for outdoor activities", 210.00, getRandomImageUrl(imageUrls, random), true, 12, new Date(), new Date()),
					new Product(categories.get(2), brands.get(5), "SKU007", "New Balance City Sneakers", "Stylish sneakers for city wear", 130.00, getRandomImageUrl(imageUrls, random), true, 50, new Date(), new Date()),
					new Product(categories.get(2), brands.get(6), "SKU008", "Asics Urban Shoes", "Comfortable and stylish city shoes", 140.00, getRandomImageUrl(imageUrls, random), true, 40, new Date(), new Date()),
					new Product(categories.get(2), brands.get(7), "SKU009", "Under Armour Casual", "Everyday casual shoes", 160.00, getRandomImageUrl(imageUrls, random), true, 35, new Date(), new Date()),
					new Product(categories.get(3), brands.get(8), "SKU010", "Saucony Training Shoes", "High-performance training shoes", 145.00, getRandomImageUrl(imageUrls, random), true, 28, new Date(), new Date()),
					new Product(categories.get(3), brands.get(9), "SKU011", "Mizuno Gym Shoes", "Durable and comfortable gym shoes", 155.00, getRandomImageUrl(imageUrls, random), true, 32, new Date(), new Date()),
					new Product(categories.get(4), brands.get(0), "SKU012", "Veja Casual Sneakers", "Casual sneakers for everyday wear", 125.00, getRandomImageUrl(imageUrls, random), true, 45, new Date(), new Date()),
					new Product(categories.get(5), brands.get(1), "SKU013", "Adidas Basketball Shoes", "High-performance basketball shoes", 190.00, getRandomImageUrl(imageUrls, random), true, 22, new Date(), new Date()),
					new Product(categories.get(5), brands.get(2), "SKU014", "Nike Air Jordans", "Iconic basketball shoes", 220.00, getRandomImageUrl(imageUrls, random), true, 18, new Date(), new Date()),
					new Product(categories.get(6), brands.get(3), "SKU015", "Puma Soccer Boots", "Professional soccer boots", 175.00, getRandomImageUrl(imageUrls, random), true, 27, new Date(), new Date()),
					new Product(categories.get(6), brands.get(4), "SKU016", "Reebok Soccer Cleats", "Durable soccer cleats", 165.00, getRandomImageUrl(imageUrls, random), true, 25, new Date(), new Date()),
					new Product(categories.get(7), brands.get(5), "SKU017", "New Balance Tennis Shoes", "High-performance tennis shoes", 160.00, getRandomImageUrl(imageUrls, random), true, 19, new Date(), new Date()),
					new Product(categories.get(8), brands.get(6), "SKU018", "Asics Golf Shoes", "Comfortable golf shoes", 170.00, getRandomImageUrl(imageUrls, random), true, 21, new Date(), new Date()),
					new Product(categories.get(9), brands.get(7), "SKU019", "Under Armour Skate Shoes", "Durable skateboarding shoes", 130.00, getRandomImageUrl(imageUrls, random), true, 34, new Date(), new Date()),
					new Product(categories.get(9), brands.get(8), "SKU020", "Saucony Skater Shoes", "Stylish and comfortable skate shoes", 135.00, getRandomImageUrl(imageUrls, random), true, 36, new Date(), new Date())
			);


			//brandRepository.saveAll(brands);
			//categoryRepository.saveAll(categories);
			//deliveryMethodRepository.saveAll(deliveryMethods);
			//productRepository.saveAll(products);
		};
	}

	private String getRandomImageUrl(String[] imageUrls, Random random) {
		return imageUrls[random.nextInt(imageUrls.length)];
	}
}
