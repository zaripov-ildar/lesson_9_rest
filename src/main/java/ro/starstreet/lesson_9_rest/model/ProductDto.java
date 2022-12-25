package ro.starstreet.lesson_9_rest.model;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    public ProductDto() {
    }
}
