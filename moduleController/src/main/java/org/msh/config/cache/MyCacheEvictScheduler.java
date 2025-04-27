package org.msh.config.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MyCacheEvictScheduler {

    //Notes : use this annotation above methods :     @CacheEvict(cacheNames = "myApiCache5min", key = "'blog_' + #id")
    @CacheEvict("myApiCache5min")
    @Scheduled(fixedDelay = 300000) //every 15 minutes
    public void evictMyApiCache5min()
    {
        System.out.println("myApiCache5min evicted ::: "+ LocalDateTime.now().toLocalDate()
                +" _ "+ LocalDateTime.now().toLocalTime());
    }
    @CacheEvict("myApiCache15min")
    @Scheduled(fixedDelay = 900000) //every 15 minutes
    public void evictMyApiCache15min()
    {
        System.out.println("myApiCache15min evicted ::: "+ LocalDateTime.now().toLocalDate()
                +" _ "+ LocalDateTime.now().toLocalTime());
    }
    @CacheEvict("myApiCache30min")
    @Scheduled(fixedDelay = 1800000) //every 30 minutes
    public void evictMyApiCache30min()
    {
        System.out.println("myApiCache30min evicted ::: "+ LocalDateTime.now().toLocalDate()
                +" _ "+ LocalDateTime.now().toLocalTime());
    }
}


