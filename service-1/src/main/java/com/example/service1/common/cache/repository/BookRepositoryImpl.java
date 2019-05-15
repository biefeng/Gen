package com.example.service1.common.cache.repository;

import com.example.service1.common.cache.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@CacheConfig(cacheNames = "cachedBooks")
@Component
public class BookRepositoryImpl implements BookRepository {

    @Cacheable()
    @Override
    public Book getBooke(String ibns) {
        System.out.println("Loding the book into cache!");
        return new Book(ibns, "some cached books");
    }

    @Override
    public Book updateBook(String ibns) {
        return null;
    }
}
