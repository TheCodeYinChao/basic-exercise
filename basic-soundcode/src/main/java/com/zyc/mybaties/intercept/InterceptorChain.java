package com.zyc.mybaties.intercept;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {
    private List<Intercept> intercepts;

    public InterceptorChain(List<Intercept> intercepts) {
        this.intercepts = intercepts;
    }

    public Object registerAll(Object target){
        for(Intercept intercept:intercepts){
            target = intercept.register(target);
        }
        return target;
    }

    public static void main(String[] args) {
        List<Intercept> interceptors = new ArrayList<Intercept>();
        Intercept logInterceptor = new IntercepterImpl();
        interceptors.add(logInterceptor);
        InterceptorChain interceptorChain = new InterceptorChain(interceptors);

        Target target = new TargetImpl();
        target= (Target)interceptorChain.registerAll(target);
        target.execute();
    }
}
