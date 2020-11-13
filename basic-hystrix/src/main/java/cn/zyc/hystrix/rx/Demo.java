package cn.zyc.hystrix.rx;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

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

    }

  /*  Observable novel=Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            emitter.onNext("连载1"); //可以发射多次
            emitter.onNext("连载2");
            emitter.onNext("连载3");
            emitter.onComplete();
        }
    });
    //观察者
    Observer<String> reader=new Observer<String>() {
        @Override
        public void onSubscribe(MediaDisposer.Disposable d) {
        }

        @Override
        public void onNext(String value) {
            if ("2".equals(value)){
                return;
            }
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };
        novel.subscribe(reader);*/
}
