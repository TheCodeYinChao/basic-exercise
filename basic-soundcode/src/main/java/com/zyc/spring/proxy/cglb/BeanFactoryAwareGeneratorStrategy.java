package com.zyc.spring.proxy.cglb;

import com.sun.istack.internal.Nullable;
import net.sf.cglib.core.ClassGenerator;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.transform.ClassEmitterTransformer;
import net.sf.cglib.transform.TransformingClassGenerator;
import org.objectweb.asm.Type;
import org.springframework.beans.factory.BeanFactory;

/**
 * dsc: BeanFactoryAwareGeneratorStrategy 验证spring 通过cglb代理生成对象时手动给 代理类加入一个成员变量
 * date: 2020/12/4 13:53
 * author: zyc
 */
public class BeanFactoryAwareGeneratorStrategy extends DefaultGeneratorStrategy {

    @Nullable
    private final ClassLoader classLoader;

    public BeanFactoryAwareGeneratorStrategy(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
        ClassEmitterTransformer transformer = new ClassEmitterTransformer() {
            @Override
            public void end_class() {
                /**通过自定义策略对 字节码修改添加 field 成员变量 */
                String BEAN_FACTORY_FIELD ="$$beanFactory";
                declare_field(Constants.ACC_PUBLIC, BEAN_FACTORY_FIELD, Type.getType(BeanFactory.class), null);
                super.end_class();
            }
        };
        return new TransformingClassGenerator(cg, transformer);
    }

    @Override
    public byte[] generate(ClassGenerator cg) throws Exception {
        if (this.classLoader == null) {
            return super.generate(cg);
        }

        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader;
        try {
            threadContextClassLoader = currentThread.getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
            return super.generate(cg);
        }

        boolean overrideClassLoader = !this.classLoader.equals(threadContextClassLoader);
        if (overrideClassLoader) {
            currentThread.setContextClassLoader(this.classLoader);
        }
        try {
            return super.generate(cg);
        }
        finally {
            if (overrideClassLoader) {
                // Reset original thread context ClassLoader.
                currentThread.setContextClassLoader(threadContextClassLoader);
            }
        }
    }
}
