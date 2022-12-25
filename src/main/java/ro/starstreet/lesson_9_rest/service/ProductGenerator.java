package ro.starstreet.lesson_9_rest.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.starstreet.lesson_9_rest.model.Product;
import ro.starstreet.lesson_9_rest.repository.ProductRepository;

@Service
public class ProductGenerator {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void generateProducts(){
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setTitle(faker.commerce().productName());
            product.setPrice(faker.number().numberBetween(100,1000));
            product.setDescription(faker.commerce().material());
            productRepository.save(product);
        }
    }
}
