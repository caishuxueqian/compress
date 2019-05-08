package com.github.houbb.compress.support.filter;

import com.github.houbb.compress.annotation.CommonEager;

/**
 * 过滤接口
 * 迁移到
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public interface IFilter<T> {

    /**
     * 过滤
     * @param t 元素
     * @return 结果
     */
    boolean filter(final T t);

}
