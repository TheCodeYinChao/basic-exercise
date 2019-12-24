package com.zyc.mybaties.intercept;

public class TargetImpl implements Target {
    @Override
    public void execute() {
        System.out.println("target impl");
    }
}
