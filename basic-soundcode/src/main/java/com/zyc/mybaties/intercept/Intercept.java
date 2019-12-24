package com.zyc.mybaties.intercept;

public interface Intercept {
    Object interce(Invocation invocation);

    Object register(Object target);
}
