package cn.zyc.beans.reflect.annotation;

import javax.jws.WebService;
import java.lang.annotation.*;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 14:55
 * Version ：1.0
 * Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Demo {
    String name() default "demo";

    boolean require() default true;
}
