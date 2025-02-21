package com.basicapp.basicapp.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheinInspectionService {

    @Autowired
    private CacheManager cacheManager;

    public void printCacheContents(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache != null){
            System.err.println("Cache Contents: ");
            System.err.println(Objects.requireNonNull(cache.getNativeCache().toString()));
        }
        else{
            System.out.println("No such cache: " + cacheName);
        }
    }
}
