package com.zyc.spring.ExecutorConfigurationSupport;

import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.UsesJava7;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;

import java.util.concurrent.*;

/**
 * dsc: ThreadPoolTaskSchedulerDemo spring定义的自定义线程池的扩展
 * date: 2020/12/9 14:54
 * author: zyc
 */
public class ThreadPoolTaskSchedulerDemo extends ExecutorConfigurationSupport {
    private final Object poolSizeMonitor = new Object();

    private int corePoolSize = 1;

    private int maxPoolSize = Integer.MAX_VALUE;

    private int keepAliveSeconds = 60;

    private int queueCapacity = Integer.MAX_VALUE;

    private boolean allowCoreThreadTimeOut = false;

    private TaskDecorator taskDecorator;

    private ThreadPoolExecutor threadPoolExecutor;

    @UsesJava7
    @Override
    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);

        ThreadPoolExecutor executor;
        if (this.taskDecorator != null) {
            executor = new ThreadPoolExecutor(
                    this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
                    queue, threadFactory, rejectedExecutionHandler) {
                @Override
                public void execute(Runnable command) {
                    super.execute(taskDecorator.decorate(command));
                }
            };
        }
        else {
            executor = new ThreadPoolExecutor(
                    this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
                    queue, threadFactory, rejectedExecutionHandler);

        }

        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
        return executor;
    }

    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<Runnable>(queueCapacity);
        }
        else {
            return new SynchronousQueue<Runnable>();
        }
    }
}
