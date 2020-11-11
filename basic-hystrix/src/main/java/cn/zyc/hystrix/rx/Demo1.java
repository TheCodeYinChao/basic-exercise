package cn.zyc.hystrix.rx;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.Objects;

/**
 * @author zyc
 * @date 2020/11/11
 * @time 22:48  https://www.bilibili.com/video/BV1sp4y1a7VP/?spm_id_from=333.788.videocard.0
 * @description :基于时间流编程
 */
public class Demo1 {
    public static void main(String[] args) {
        Observable.just("123")
        .map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                System.out.println("事件源输入：-----》"+s);
                return Integer.valueOf(s);
            }
        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>(){
            @Override
            public void onCompleted() {//全部结束啦
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer o) {//上游发下来的事件
                System.out.println("处理后的值"+o);
            }
        });



    }
}
