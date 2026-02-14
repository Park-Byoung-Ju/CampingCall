package com.example.demo.campingcall.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;


@EnableCaching
public class CacheConfig {

	@Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        
        // null값은 캐싱되지 않도록 설정
        cacheManager.setAllowNullValues(false);
        
        return cacheManager;
    }
}
