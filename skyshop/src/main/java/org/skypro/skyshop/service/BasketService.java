package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.dto.BasketItem;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Товар не найден"));
        productBasket.addProductToBasket(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getBasket().entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found in storage"));
                    return new BasketItem(product, quantity);
                })
                .toList();
        return new UserBasket(basketItems);
    }
}
