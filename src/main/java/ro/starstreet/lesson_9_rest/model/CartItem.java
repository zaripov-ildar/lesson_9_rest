package ro.starstreet.lesson_9_rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private String title;
    private int amount;

    public void add(int amount) {
        this.amount += amount;
    }
}
