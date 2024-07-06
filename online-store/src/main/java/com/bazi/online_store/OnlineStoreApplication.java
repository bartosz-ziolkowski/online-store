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
			Brand ganni = new Brand("GANNI");
			Brand cphApparel = new Brand("CPH APPAREL");
			Brand reserved = new Brand("RESERVED");
			//brandRepository.saveAll(Arrays.asList(ganni, cphApparel, reserved));

			Category shoes = new Category("Shoes");
			Category clothes = new Category("Clothes");
			Category sport = new Category("Sport");
			//categoryRepository.saveAll(Arrays.asList(shoes, clothes, sport));

			Product[] products = new Product[]{
					new Product(shoes, ganni, "SKU001", "GANNI Sneakers", "Stylish sneakers for everyday wear", 120.00, "url1.png", true, 50, new Date(), new Date()),
					new Product(shoes, cphApparel, "SKU002", "CPH APPAREL Running Shoes", "Comfortable running shoes", 100.00, "url2.png", true, 30, new Date(), new Date()),
					new Product(shoes, reserved, "SKU003", "RESERVED Sandals", "Elegant sandals for summer", 80.00, "url3.png", true, 20, new Date(), new Date()),
					new Product(clothes, ganni, "SKU004", "GANNI T-Shirt", "Casual t-shirt", 40.00, "url4.png", true, 100, new Date(), new Date()),
					new Product(clothes, cphApparel, "SKU005", "CPH APPAREL Jacket", "Warm winter jacket", 150.00, "url5.png", true, 10, new Date(), new Date()),
					new Product(clothes, reserved, "SKU006", "RESERVED Jeans", "Classic blue jeans", 60.00, "url6.png", true, 70, new Date(), new Date()),
					new Product(sport, ganni, "SKU007", "GANNI Sportswear", "Comfortable sportswear", 90.00, "url7.png", true, 25, new Date(), new Date()),
					new Product(sport, cphApparel, "SKU008", "CPH APPAREL Yoga Pants", "Flexible yoga pants", 70.00, "url8.png", true, 40, new Date(), new Date()),
					new Product(sport, reserved, "SKU009", "RESERVED Sports T-Shirt", "Breathable sports t-shirt", 50.00, "url9.png", true, 80, new Date(), new Date()),
					new Product(shoes, ganni, "SKU010", "GANNI Boots", "Durable boots", 130.00, "url10.png", true, 15, new Date(), new Date()),
					new Product(clothes, cphApparel, "SKU011", "CPH APPAREL Dress", "Elegant evening dress", 200.00, "url11.png", true, 5, new Date(), new Date()),
					new Product(clothes, reserved, "SKU012", "RESERVED Hoodie", "Comfortable hoodie", 55.00, "url12.png", true, 60, new Date(), new Date()),
					new Product(sport, ganni, "SKU013", "GANNI Running Shorts", "Lightweight running shorts", 45.00, "url13.png", true, 35, new Date(), new Date()),
					new Product(shoes, cphApparel, "SKU014", "CPH APPAREL Slippers", "Comfortable slippers", 30.00, "url14.png", true, 25, new Date(), new Date()),
					new Product(clothes, ganni, "SKU015", "GANNI Blouse", "Elegant blouse", 70.00, "url15.png", true, 50, new Date(), new Date()),
					new Product(clothes, cphApparel, "SKU016", "CPH APPAREL Shorts", "Casual shorts", 35.00, "url16.png", true, 40, new Date(), new Date()),
					new Product(sport, reserved, "SKU017", "RESERVED Tracksuit", "Comfortable tracksuit", 120.00, "url17.png", true, 20, new Date(), new Date()),
					new Product(shoes, ganni, "SKU018", "GANNI Loafers", "Stylish loafers", 110.00, "url18.png", true, 18, new Date(), new Date()),
					new Product(clothes, reserved, "SKU019", "RESERVED Sweater", "Warm sweater", 60.00, "url19.png", true, 55, new Date(), new Date()),
					new Product(sport, cphApparel, "SKU020", "CPH APPAREL Gym Bag", "Spacious gym bag", 40.00, "url20.png", true, 30, new Date(), new Date())
			};

			//productRepository.saveAll(Arrays.asList(products));
		};
	}
}
