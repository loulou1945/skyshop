package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    public abstract boolean isSpecial();

    private final String productName;

    public Product(UUID id, String productName) {
        this.id = id;
        if (productName.isBlank()) {
            throw new IllegalArgumentException("Неправильное имя продукта");
        }
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return productName + ": " + getPrice();
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return productName + ": " + getPrice();
    }

    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
