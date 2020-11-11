package com.zyc.valid;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * description: ValidDemo <br>
 * date: 2020/9/1 17:51 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class ValidDemo {
    public static void main(String[] args) {

        Person person = new Person();
        person.setAge(-1);

        // 1、使用【默认配置】得到一个校验工厂  这个配置可以来自于provider、SPI提供
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        // 2、得到一个校验器
        Validator validator = validatorFactory.getValidator();
        // 3、校验Java Bean（解析注解） 返回校验结果
        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // 输出校验结果
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }
}
