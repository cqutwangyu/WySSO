package com.wy.sso;

import com.wy.sso.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SSOApplicationTests {
    @Autowired
    RedisCache redisCache;

    @Test
    void contextLoads() {
        redisCache.setCacheObject("1","2");
        Object cacheObject = redisCache.getCacheObject("1");
        System.out.println(cacheObject);
    }

}
