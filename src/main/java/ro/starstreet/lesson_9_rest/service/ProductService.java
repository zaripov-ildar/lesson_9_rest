package ro.starstreet.lesson_9_rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.starstreet.lesson_9_rest.model.Product;
import ro.starstreet.lesson_9_rest.model.ProductDto;
import ro.starstreet.lesson_9_rest.repository.ProductRepository;
import ro.starstreet.lesson_9_rest.repository.specifications.ProductSpecification;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<ProductDto> find(Integer minPrice, Integer maxPrice, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and((ProductSpecification.priceLessOrEqualsThan(maxPrice)));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }
        return repository.findAll(spec, PageRequest.of(page - 1, 10))
                .map(ProductDto::new);
    }

    public ProductDto save(ProductDto productDto) {
        return new ProductDto(repository.save(new Product(productDto)));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public ProductDto saveOrUpdate(ProductDto productDto) {
        if (productDto.getId() == null) {
            productDto.setId(-1L);
        }
        System.out.println(productDto);
        return repository.findById(productDto.getId())
                .map(product -> {
                    product.setTitle(productDto.getTitle());
                    product.setPrice(productDto.getPrice());
                    return new ProductDto(repository.save(product));
                }).orElseGet(() -> save(productDto));
    }

    public ProductDto findById(Long id) {
        return repository.findById(id)
                .map(ProductDto::new)
                .orElseGet(ProductDto::new);
    }
}
