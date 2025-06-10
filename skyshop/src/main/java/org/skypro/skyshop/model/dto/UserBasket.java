package org.skypro.skyshop.model.dto;

import org.skypro.skyshop.model.basket.ProductBasket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items, double total) {
        this.items = items;
        this.total = total;
    }

    public UserBasket(List<BasketItem> items) {
        this(
                items,
                items.stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum()
        );
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
