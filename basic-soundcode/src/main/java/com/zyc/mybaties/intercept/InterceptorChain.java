package com.zyc.mybaties.intercept;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {
    private List<Intercept> intercepts =new ArrayList<>();


    public Object registerAll(Object target){
        for(Intercept intercept:intercepts){
            target = intercept.register(target);
        }
        return target;
    }
}
