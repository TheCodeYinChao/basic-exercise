package com.cache.controller;

import com.cache.sevice.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 2020/4/4.
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;


    @GetMapping("/getCache")
    public Object getCache(@RequestParam String orderId) {
        return cacheService.queryOrder(orderId);
    }
}
