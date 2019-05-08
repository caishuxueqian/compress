package com.github.houbb.compress.support.filter;

import com.github.houbb.compress.annotation.CommonEager;

/**
 * 条件接口
 * 迁移到
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public interface ICondition<T> {

    /**
     * 满足条件
     * @param t 元素
     * @return 结果
     */
    boolean condition(final T t);

}
