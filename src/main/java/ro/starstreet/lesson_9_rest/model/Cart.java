package ro.starstreet.lesson_9_rest.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    private final Map<Long, CartItem> cartMap;

    public Cart() {
        cartMap = new HashMap<>();
    }

    public void removeFromCart(long id) {
        cartMap.remove(id);
    }

    public Map<Long, CartItem> getCartMap() {
        return cartMap;
    }

    public void changeAmount(ProductDto productDto, int amount) {
        Long id = productDto.getId();
        if (!cartMap.containsKey(id)) {
            cartMap.put(id, new CartItem(productDto.getTitle(), 0));
        }
        cartMap.get(id).add(amount);
        if (cartMap.get(id).getAmount() == 0) {
            removeFromCart(id);
        }
    }
}
