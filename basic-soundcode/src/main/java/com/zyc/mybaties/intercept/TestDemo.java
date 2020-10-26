package com.zyc.mybaties.intercept;

public class TestDemo {

    public static void main(String[] args) {
        Target ta = new TargetImpl();

        TargetProxy targetProxy = new TargetProxy(ta);
        targetProxy.addInteceper(new IntercepterImpl());

        Target waper = (Target) TargetProxy.waper(ta, targetProxy);

        waper.execute();
    }
}
