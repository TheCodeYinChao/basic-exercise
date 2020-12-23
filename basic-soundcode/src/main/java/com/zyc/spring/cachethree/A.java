package com.zyc.spring.cachethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * dsc: A
 * date: 2020/12/14 17:46
 * author: zyc
 */
@Service
public class A {
    @Autowired
    private B b;
}
