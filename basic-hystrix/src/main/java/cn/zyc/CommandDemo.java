package cn.zyc;

import com.netflix.hystrix.*;

/**
 * description: CommandDemo <br>
 * date: 2020/6/4 17:28 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class CommandDemo extends HystrixCommand {

    public CommandDemo(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGrop"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withMaxQueueSize(10)
                                .withCoreSize(2)
                )
        );
    }


    @Override
    protected Object run() throws Exception {
        System.out.println("执行业务逻辑");
        return null;
    }
}
