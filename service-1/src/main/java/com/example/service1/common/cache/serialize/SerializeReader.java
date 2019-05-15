package com.example.service1.common.cache.serialize;

import com.example.service1.common.cache.Book;
import com.example.service1.common.util.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public class SerializeReader<T> implements RedisElementReader<T> {

    @Autowired
    private SerializeUtils serializeUtils;


    @Override
    public T read(ByteBuffer buffer) {
        return null;
    }
}
