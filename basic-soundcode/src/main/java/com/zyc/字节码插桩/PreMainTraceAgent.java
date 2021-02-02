package com.zyc.字节码插桩;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 这里是启动前， 相反会有启动vm之后
 *
 * 启动前我们可以对字节码随意修改
 * 但是启动后就会有限制  我们只能对函数进行扩展，不能修改变量
 *
 * https://tech.meituan.com/2019/02/28/java-dynamic-trace.html
 * https://www.cnblogs.com/rickiyang/p/11368932.html
 * dsc: PreMainTraceAgent
 * date: 2021/2/2 14:06
 * author: zyc
 */
public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new DefineTransformer(), true);
    }

    static class DefineTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class:" + className);
            return classfileBuffer;
        }
    }
}
