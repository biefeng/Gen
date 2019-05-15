package com.example.service1.common.cache.serialize;

import org.springframework.data.redis.serializer.RedisElementWriter;

import java.nio.ByteBuffer;

public class SerializeWriter implements RedisElementWriter {
    @Override
    public ByteBuffer write(Object element) {
        return null;
    }
}
