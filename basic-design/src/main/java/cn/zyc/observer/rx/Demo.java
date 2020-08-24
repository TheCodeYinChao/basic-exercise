package cn.zyc.observer.rx;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * description: Demo  rxjava  入门 <br>
 * date: 2020/8/14 11:34 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Demo {

    /**
     * 基础写法
     */
    @Test
    public  void test(){


        Observable<String> objectObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }

        };

        objectObservable.subscribe(mySubscriber);
    }

    /**
     * 变种写法一
     */
    @Test
    public  void test1(){


        Observable<String> objectObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );


        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        Action0 onCompleted = new Action0() {
            @Override
            public void call() {
                System.out.println();
            }
        };

        Action1<Throwable> e = new Action1<Throwable>() {
            @Override
            public void call(Throwable s) {
                System.out.println(s);
            }
        };

        objectObservable.subscribe(onNextAction,e,onCompleted);
    }

    /**
     * jdk 简化写法
     */
    @Test
    public  void test2Jdk8(){
        Observable.just("Hello, world!")
                .subscribe(s -> System.out.println(s));
    }

    /**************  操作符（Operators）************************/

    /**
     *一个事件转换为另一个事件 map
     */
    @Test
    public void map(){

        /**
         * 1 为普通版rx java 的使用
         * 2 升级成map jdk8 简化操作
         *
         * 3 通过 sub中 操作来改变最终的结果 这里原则上 sub 做的事情越少越好
         * 4 这里把map组合 中处理不同业务 同时也在此体现出响应式中的流的数据
         */
        //1
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -Dan";
                    }
                })
                .subscribe(s -> System.out.println(s));
        //2
        Observable.just("Hello, world!")
                .map(s->  s + " -Dan")
                .subscribe(s -> System.out.println(s));
        //3
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));

        //4
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }


    /**
     * 业务例子1 查询字符显示结果
     */

    @Test
    public void operFrom(){
    /**
     * Observable<List<String>> query(String text);
     * query("Hello, world!")
     *     .subscribe(urls -> {
     *         for (String url : urls) {
     *             System.out.println(url);
     *         }
     *     });
     */
        String [] a = {"url1", "url2", "url3"};
        Observable.from(a)
                .map(x-> {
                    return x;
                })
                .subscribe(url -> System.out.println(url));

    }

    public void flatMap(){
        /**
         * query("Hello, world!")
         *     .flatMap(new Func1<List<String>,
         *          Observable<String>>() {//这里把list 变成form
         *               @Override
         *               public Observable<String> call(List<String> urls) {
         *                   return Observable.from(urls);
         *               }
         *           }
         *     )
         *     .subscribe(url -> System.out.println(url));
         *
         *
         *
         *
         *
         *      query("Hello, world!")
         *     .flatMap(urls -> Observable.from(urls))
         *     .flatMap(new Func1<String, Observable<String>>() {
         *         @Override
         *         public Observable<String> call(String url) {
         *             return getTitle(url);
         *         }
         *     })
         *     .subscribe(title -> System.out.println(title));
         *
         *
         *     query("Hello, world!")
         *     .flatMap(urls -> Observable.from(urls))
         *     .flatMap(url -> getTitle(url))
         *     .subscribe(title -> System.out.println(title));
         *
         *
         *
         *     query("Hello, world!")
         *     .flatMap(urls -> Observable.from(urls))
         *     .flatMap(url -> getTitle(url))
         *     .filter(title -> title != null)
         *     .take(5) //只想取5个结果
         *     .subscribe(title -> System.out.println(title));
         *
         *
         *     query("Hello, world!")
         *     .flatMap(urls -> Observable.from(urls))
         *     .flatMap(url -> getTitle(url))
         *     .filter(title -> title != null)
         *     .take(5)
         *     .doOnNext(title -> saveTitle(title)) //每次输出一个元素前做一些额外的事情
         *     .subscribe(title -> System.out.println(title));
         */

    }

    @Test
    public  void testONcOMOnError(){
        /**
         * Observable.just("Hello, world!")
         *     .map(s -> potentialException(s))
         *     .map(s -> anotherPotentialException(s))
         *     .subscribe(new Subscriber<String>() {
         *         @Override
         *         public void onNext(String s) { System.out.println(s); }
         *
         *         @Override
         *         public void onCompleted() { System.out.println("Completed!"); }
         *
         *         @Override
         *         public void onError(Throwable e) { System.out.println("Ouch!"); }
         *     });
         */
    }

    /******************  调度器  Subscription这个是桥梁 ***********************/
    @Test
    public void testRe(){
        /**
         * myObservableServices.retrieveImage(url)
         *     .subscribeOn(Schedulers.io())//指定观察者运行线程
         *     .observeOn(AndroidSchedulers.mainThread())//指定订阅者运行的线程：AndroidSchedulers这个只针对安卓的线程调度器 这里只是个例子
         *     .subscribe(bitmap -> myImageView.setImageBitmap(bitmap));
         */
    }

}
