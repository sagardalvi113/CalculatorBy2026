package com.bakery.config;

import com.bakery.model.Product;
import com.bakery.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(
                "Chocolate Cake", 
                "Rich and moist chocolate cake with chocolate frosting", 
                25.99, 
                "Cakes", 
                10,
                "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=400"
            ));

            productRepository.save(new Product(
                "Vanilla Cupcake", 
                "Light and fluffy vanilla cupcakes with buttercream", 
                3.50, 
                "Cupcakes", 
                50,
                "https://images.unsplash.com/photo-1614707267537-b85aaf00c4b7?w=400"
            ));

            productRepository.save(new Product(
                "Croissant", 
                "Buttery and flaky French croissant", 
                4.25, 
                "Pastries", 
                30,
                "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=400"
            ));

            productRepository.save(new Product(
                "Sourdough Bread", 
                "Artisan sourdough bread with crispy crust", 
                8.50, 
                "Breads", 
                15,
                "https://images.unsplash.com/photo-1549931319-a545dcf3bc73?w=400"
            ));

            productRepository.save(new Product(
                "Blueberry Muffin", 
                "Fresh blueberry muffins with streusel topping", 
                3.75, 
                "Muffins", 
                40,
                "https://images.unsplash.com/photo-1607958996333-41aef7caefaa?w=400"
            ));

            productRepository.save(new Product(
                "Red Velvet Cake", 
                "Classic red velvet cake with cream cheese frosting", 
                28.99, 
                "Cakes", 
                8,
                "https://images.unsplash.com/photo-1586985289688-ca3cf47d3e6e?w=400"
            ));

            productRepository.save(new Product(
                "Cinnamon Roll", 
                "Sweet cinnamon roll with cream cheese glaze", 
                5.25, 
                "Pastries", 
                25,
                "https://images.unsplash.com/photo-1619985632461-f33748ef8f3e?w=400"
            ));

            productRepository.save(new Product(
                "Apple Pie", 
                "Homemade apple pie with flaky crust", 
                18.99, 
                "Pies", 
                12,
                "https://images.unsplash.com/photo-1535920527002-b35e96722eb9?w=400"
            ));

            productRepository.save(new Product(
                "Chocolate Chip Cookie", 
                "Classic chocolate chip cookies (6 pack)", 
                6.50, 
                "Cookies", 
                60,
                "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400"
            ));

            productRepository.save(new Product(
                "Baguette", 
                "Traditional French baguette", 
                3.99, 
                "Breads", 
                20,
                "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400"
            ));
        };
    }
}
