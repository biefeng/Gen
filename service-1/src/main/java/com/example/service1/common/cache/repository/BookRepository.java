package com.example.service1.common.cache.repository;

import com.example.service1.common.cache.Book;

public interface BookRepository {

    Book getBooke(String ibns);

    Book updateBook(String ibns);
}
