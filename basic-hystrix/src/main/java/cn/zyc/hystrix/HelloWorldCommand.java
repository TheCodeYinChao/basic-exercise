package cn.zyc.hystrix;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * description: HelloWorldCommand <br>
 * date: 2020/8/13 17:32 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;
    public HelloWorldCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("aa"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("aaa"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)
                )
        );
        this.name = name;
    }
    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }
    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }
    public static void main(String[] args) throws Exception{
        HelloWorldCommand command = new HelloWorldCommand("test-Fallback");
        String result = command.execute();
        System.out.println(result);
    }
}
