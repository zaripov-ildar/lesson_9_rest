package ro.starstreet.lesson_9_rest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.starstreet.lesson_9_rest.model.Cart;
import ro.starstreet.lesson_9_rest.model.CartItem;
import ro.starstreet.lesson_9_rest.model.ProductDto;
import ro.starstreet.lesson_9_rest.service.ProductService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final Cart cart;

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {

        page = page == null || page < 1 ? 1 : page;
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
        cart.removeFromCart(id);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/cart")
    public Map<Long, CartItem> addToCart() {
        return cart.getCartMap();
    }

    @PutMapping("/cart/changeAmount")
    public Map<Long, CartItem> decreaseProductAmountInCart(@RequestParam long id, @RequestParam int amount) {
        cart.changeAmount(productService.findById(id), amount);
        return cart.getCartMap();
    }

    @DeleteMapping("/cart/{id}")
    public void removeFromCart(@PathVariable long id) {
        log.debug("trying to delete");
        cart.removeFromCart(id);
    }


}
