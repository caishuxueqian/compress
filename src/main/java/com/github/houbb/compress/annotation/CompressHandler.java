package com.github.houbb.compress.annotation;

import java.lang.annotation.*;

/**
 * Compress 处理器
 * 1. 放在用户定义的处理器上。
 * @author binbin.hou
 * @since 0.0.1
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompressHandler {

    /**
     * 制定 handler 的名称
     * 1. 如果不指定则默认使用类名简称
     * 2. 如果存在重复，则后缀依次递增加数字,从下标志1开始。如：handler1,handler2...
     * 3. 因为允许添加一个 handler 多次，所以重复很正常。
     * @return 名称
     */
    String value() default "";

}
