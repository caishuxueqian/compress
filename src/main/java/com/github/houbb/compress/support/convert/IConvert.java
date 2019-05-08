package com.github.houbb.compress.support.convert;


import com.github.houbb.compress.annotation.CommonEager;

/**
 * 转换类接口
 * 后续换成 heaven 包接口。
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public interface IConvert<R, T> {

    /**
     * 转换
     * @param r 原始信息
     * @return 转换后的对象
     */
    T convert(R r);

}
