package cn.zyc;

import com.netflix.hystrix.*;

/**
 * description: CommandDemo <br>
 * date: 2020/6/4 17:28 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class CommandDemo extends HystrixCommand<Integer> {

    CommandDemo(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGrop"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withMaxQueueSize(10)
                                .withCoreSize(2)
                )
               
        );
    }


    @Override
    protected Integer run() throws Exception {
        System.out.println("执行业务逻辑");
        Thread.sleep(500);
        return 1;
    }

    @Override
    protected Integer getFallback() {
        return -1;
    }

    public static void main(String[] args) {
        //线程隔离执行时 的线线程是内部的线程
        new Thread(()->{
                CommandDemo demo = new CommandDemo("sql");
                Integer execute = demo.execute();
                System.out.println(execute);
        }).start();

        new Thread(()->{
             CommandDemo demo1= new CommandDemo("sql");
             Integer execute1 = demo1.execute();
             System.out.println(execute1);
        }).start();
    }
}
