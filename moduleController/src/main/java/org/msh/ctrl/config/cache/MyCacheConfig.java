package org.msh.ctrl.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
public class MyCacheConfig {

    @Bean
    public ConcurrentMapCacheManager cacheManager(){
        return new ConcurrentMapCacheManager("myApiCache5min", "myApiCache15min", "myApiCache30min");
    }


}
