package ro.starstreet.lesson_9_rest.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ro.starstreet.lesson_9_rest.model.entities.Product;

public class ProductSpecification {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> priceLessOrEqualsThan(Integer price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

}
