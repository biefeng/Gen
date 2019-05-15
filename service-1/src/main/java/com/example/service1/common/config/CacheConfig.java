package com.example.service1.common.config;

import com.example.service1.common.util.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.ObjectOutputStream;

@Configuration
public class CacheConfig {

    @Autowired
    private SerializeUtils serializeUtils;


    //@Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        RedisSerializationContext.SerializationPair serializationPair = new RedisSerializationContext.SerializationPair() {
            @Override
            public RedisElementReader getReader() {
                return null;
            }

            @Override
            public RedisElementWriter getWriter() {
                return null;
            }
        };
        cacheConfiguration.serializeKeysWith(serializationPair);


        return cacheConfiguration;
    }
}
