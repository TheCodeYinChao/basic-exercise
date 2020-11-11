package cn.zyc.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * description: HelloWorldHystrixCommand <br>
 * date: 2020/8/13 16:25 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class HelloWorldHystrixCommand extends HystrixCommand <String>{
    private final String name;

    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    // 如果继承的是HystrixObservableCommand，要重写Observable construct()
    @Override
    protected String run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }

    public static void main(String[] args) throws Exception {
        test1();
    }
    public static void test1() throws Exception{
        String result;
        HelloWorldHystrixCommand helloWorldCommand;
        //每个Command对象只能调用一次,不可以重复调用,
        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
//         helloWorldCommand = new HelloWorldHystrixCommand("Synchronous-hystrix");
        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
//         result = helloWorldCommand.execute();
//        System.out.println("result=" + result);

        helloWorldCommand = new HelloWorldHystrixCommand("Asynchronous-hystrix");
        //异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        result = future.get(1000, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);

        System.out.println("mainThread=" + Thread.currentThread().getName());
    }
    public static void test2(){
        //注册观察者事件拦截
        Observable<String> fs = new HelloWorldHystrixCommand("World").observe();
        //注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
            }
        });
        //注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
            }
        });
        /* 运行结果
        call execute result=Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
        onNext: Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
        execute onCompleted
        */
    }
}
