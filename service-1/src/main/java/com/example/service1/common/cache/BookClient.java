package com.example.service1.common.cache;

import com.example.service1.common.cache.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.stereotype.Component;

@Component
public class BookClient implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookClient.class);

    @Autowired
    private BookRepository bookRepository;



    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("get the book : {}",bookRepository.getBooke("ibns1234"));
        LOGGER.info("get the book : {}",bookRepository.getBooke("ibns2345"));
        LOGGER.info("get the book : {}",bookRepository.getBooke("ibns1234"));
        LOGGER.info("get the book : {}",bookRepository.getBooke("ibns12345"));
        LOGGER.info("get the book : {}",bookRepository.getBooke("ibns2345"));

    }
}
