package cn.zyc.hystrix.demonstrator;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.InputStream;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * dsc: HelloWorldCommand
 * date: 2020/11/13 16:48
 * author: zyc
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;
    public HelloWorldCommand(String name) {
        //最少配置:指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    @Override
    protected String run() {
        // 依赖逻辑封装在run()方法中
        return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args)throws Exception {
//        t1();
        t2();
    }
    //调用实例
    public static void t1() throws Exception{
        //每个Command对象只能调用一次,不可以重复调用,
        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        //使用execute(),效果等同于:helloWorldCommand.queue().get();
        /**同步调用代码*/
        String result = helloWorldCommand.execute();
        System.out.println("result=" + result);

        helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        //异步调用,可自由控制获取结果时机,
        /**异步调用代码*/
        Future<String> future = helloWorldCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());
    }

    /**
     * 注册异步事件回调执行
     */
    public static void t2()throws Exception{
        //注册观察者事件拦截
        Observable<String> fs = new HelloWorldCommand("World").observe();
        //注册结果回调事件
                fs.subscribe(new Action1<String>() {
                    @Override
                    public void call(String result) {
                        //执行结果处理,result 为HelloWorldCommand返回的结果
                        //用户对结果做二次处理.
                        System.out.println(" call execute result="+result);
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

        System.in.read();//阻塞等待结果获取
        /* 运行结果
        call execute result=Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
        onNext: Hello observe-hystrix thread:hystrix-HelloWorldGroup-3
        execute onCompleted
        */
    }

}
//运行结果: run()方法在不同的线程下执行
// result=Hello Synchronous-hystrix thread:hystrix-HelloWorldGroup-1
// result=Hello Asynchronous-hystrix thread:hystrix-HelloWorldGroup-2
// mainThread=main