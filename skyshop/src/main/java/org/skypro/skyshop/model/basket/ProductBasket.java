package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProductToBasket(UUID id) {
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(products);
    }
}
