package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrise;
    private final int discount;

    public DiscountedProduct(UUID id, String productName, int basePrise, int discount) {
        super(id, productName);
        if (basePrise <= 0) {
            throw new IllegalArgumentException("Неверно указана цена продукта");
        }
        this.basePrise = basePrise;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Неверно указана скидка продукта");
        }
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public double getPrice() {
        return basePrise - (double) basePrise * discount / 100;
    }

    @Override
    public String toString() {
        return getProductName() + ": " + getPrice() + " (" + getDiscount() + "%)";
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
