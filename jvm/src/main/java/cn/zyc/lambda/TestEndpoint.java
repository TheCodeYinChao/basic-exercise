package cn.zyc.lambda;


import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * description: TestEndpoint <br>
 * date: 2020/8/19 11:10 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */

public class TestEndpoint {

    @Test
    public void test1(){
        CustomInterface s = x-> System.out.println(x);
        s.service("TEST");

        CustomInterface s1 = (x)-> System.out.println(x);

        s1.service("TEST1");

        CustomInterface s2 = (x)-> {
            System.out.println(x);
        };
        s2.service("TEST2");
    }
    @Test
    public void testFunc(){
        BiConsumer<Integer,Integer> b = ( x, y)-> System.out.println(x+y);

        BiConsumer<Integer,Integer> b1 = ( x, y)-> System.out.println(x*y);
        b.andThen(b1).accept(1,2);

        ThreadLocal<Object> objectThreadLocal = ThreadLocal.withInitial(() ->
             10000
        );
        System.out.println(objectThreadLocal.get());
    }

    @Test
    public void stream(){
        Stream<String> a = Stream.of("a", "b", "c");

        long a1 = a.filter(x -> x.equals("a")).count();

        List<String> b = a.filter(x -> x.equals("b")).collect(toList());
    }

    @Test
    public void map(){ //数据转换
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase()).collect(toList());
        System.out.println(collected);
    }

    @Test
    public void mapflat(){
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        System.out.println(together);
    }

    @Test
    public void minMAX(){
       List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .min(Comparator.comparing(x-> x.size()))
                .get();
        System.out.println(together);

        List<Integer> together1 = Stream.of(asList(1, 2), asList(3, 4))
                .max(Comparator.comparing(x-> x.size()))
                .get();
        System.out.println(together1);
    }

    @Test
    public void reduce(){
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);
    }

    @Test
    public void Optional(){
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());
    }

    /**并行化 要数据足够大才能体现出来  否则 比串行化 更耗费性能*/
}
