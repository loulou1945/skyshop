package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void whenStorageIsEmpty_thenReturnsEmptyList() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        List<SearchResult> result = (List<SearchResult>) searchService.search("any");
        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllSearchable();
    }

    @Test
    void whenNoMatch_thenReturnsEmptyList() {
        Searchable article = mock(Searchable.class);
        when(article.getSearchTerm()).thenReturn("Article about apples");

        Searchable product = mock(Searchable.class);
        when(product.getSearchTerm()).thenReturn("Apple");

        when(storageService.getAllSearchable()).thenReturn(List.of(article, product));

        List<SearchResult> result = (List<SearchResult>) searchService.search("Apple");

        assertTrue(result.isEmpty());
        verify(article, atLeastOnce()).getSearchTerm();
        verify(product, atLeastOnce()).getSearchTerm();
    }

    @Test
    void whenMatchesExist_thenReturnsResult() {
        Product product = new SimpleProduct(UUID.randomUUID(), "Apple", 150);
        Article article = new Article(UUID.randomUUID(), "Apple", "Apple don't sink");

        when(storageService.getAllSearchable()).thenReturn(List.of(product, article));

        List<SearchResult> result = (List<SearchResult>) searchService.search("Apple");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getName().contains("Apple")));
        assertTrue(result.stream().allMatch(r -> r.getName().contains("Apple")));
    }
}
