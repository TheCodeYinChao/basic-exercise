package com.zyc.spring.lifecycle;

import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class MyLifecycle implements SmartLifecycle, LifecycleProcessor {
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("停止");
    }

    @Override
    public void start() {
        System.out.println("开始");
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClose() {
        System.out.println("关闭");
    }
}
