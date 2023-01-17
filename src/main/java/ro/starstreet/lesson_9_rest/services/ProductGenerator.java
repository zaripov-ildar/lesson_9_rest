package ro.starstreet.lesson_9_rest.services;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.starstreet.lesson_9_rest.model.entities.Product;
import ro.starstreet.lesson_9_rest.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductGenerator {
    private final ProductRepository productRepository;


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
