package cn.zyc.hystrix.rx;

import rx.Observable;
import rx.Subscriber;

/**
 * description: Demo <br>
 * date: 2020/8/14 11:34 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Demo {
    public static void main(String[] args) {
        test();

    }


    public static void test(){


        Observable<String> objectObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
//                        sub.onCompleted();
                        sub.onNext("这是第二步");
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
}
