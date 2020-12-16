package com.zyc.spring.cachethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * dsc: B
 * date: 2020/12/14 17:46
 * author: zyc
 */
@Service
public class B {
    @Autowired
    private A a;
}
