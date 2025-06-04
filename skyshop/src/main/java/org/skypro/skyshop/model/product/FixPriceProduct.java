package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private int FIX_PRICE = 170;

    @Override
    public boolean isSpecial() {
        return true;
    }

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public double getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getProductName() + ": Фиксированная цена " + getPrice();
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
