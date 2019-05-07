package com.github.houbb.compress.convert;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IConvert<R, T> {

    /**
     * 转换
     * @param r 原始信息
     * @return 转换后的对象
     */
    T convert(R r);

}
