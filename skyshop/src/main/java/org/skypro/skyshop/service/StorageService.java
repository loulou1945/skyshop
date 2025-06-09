package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService(Map<UUID, Product> products, Map<UUID, Article> articles) {
        this.products = products;
        this.articles = articles;
        createTestData();
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    private void createTestData() {

        Product apple = new FixPriceProduct(UUID.randomUUID(), "Apple");
        Product banana = new DiscountedProduct(UUID.randomUUID(), "Banana", 150, 15);
        Product kiwi = new SimpleProduct(UUID.randomUUID(), "Kiwi", 200);
        Product avocado = new SimpleProduct(UUID.randomUUID(), "Avocado", 210);
        Product apricot = new DiscountedProduct(UUID.randomUUID(), "Apricot", 170, 25);
        Product lime = new FixPriceProduct(UUID.randomUUID(), "Lime");
        Product appleGrannySmith = new DiscountedProduct(UUID.randomUUID(), "Apple: Granny Smith", 210, 10);

        this.products.put(apple.getId(), apple);
        this.products.put(banana.getId(), banana);
        this.products.put(kiwi.getId(), kiwi);
        this.products.put(avocado.getId(), avocado);
        this.products.put(apricot.getId(), apricot);
        this.products.put(lime.getId(), lime);
        this.products.put(appleGrannySmith.getId(), appleGrannySmith);

        Article appleArticle1 = new Article(UUID.randomUUID(), "Apple", "Яблоко не тонет в воде, так как на четверть состоит из воздуха");
        Article appleArticle2 = new Article(UUID.randomUUID(), "Apple", "Археологи утверждают, что яблоня - первое дерево, культивируемое людьми еще 6500 лет до наступления новой эры");
        Article bananaArticle = new Article(UUID.randomUUID(), "Banana", "С ботанической точки зрения, банан - ягода");

        this.articles.put(appleArticle1.getId(), appleArticle1);
        this.articles.put(appleArticle2.getId(), appleArticle2);
        this.articles.put(bananaArticle.getId(), bananaArticle);

    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.concat(products.values().stream(),articles.values().stream()).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}