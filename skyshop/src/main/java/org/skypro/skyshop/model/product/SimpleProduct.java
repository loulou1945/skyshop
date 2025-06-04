package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final double price;

    public SimpleProduct(UUID id, String productName, double price) {
        super(id, productName);
        if (price <= 0) {
            throw new IllegalArgumentException("Неверно указана цена продукта");
        }
        this.price = price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getPrice();
    }

    @Override
    public String getStringRepresentation() {

        return null;
    }

    @Override
    public String getName() {
        return getProductName();
    }
}
