package ro.starstreet.lesson_9_rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.starstreet.lesson_9_rest.model.ProductDto;
import ro.starstreet.lesson_9_rest.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {

        page = page == null || page < 1 ? 1 : page;
        System.out.println(titlePart);
        return productService.find(minPrice, maxPrice, titlePart, page);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        return productService.findById(id);
    }
}
