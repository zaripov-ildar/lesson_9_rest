package ro.starstreet.lesson_9_rest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    public Product(ProductDto productDto) {
        this.title = productDto.getTitle();
        this.price = productDto.getPrice();
    }

    public Product() {

    }
}
