package com.zyc.spring.cachethree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * dsc: DemoCIRLE
 * date: 2020/12/15 15:09
 * author: zyc
 */
public class DemoCIRLE {
    public static void main(String[] args) {
        List list = new ArrayList();

        Stream<String> stringStream = Stream.of("1", "2");
        //flatmap 合并流
        list.stream().flatMap(x->Stream.of(x)).count();

    }
}
