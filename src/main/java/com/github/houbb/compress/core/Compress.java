package com.github.houbb.compress.core;

/**
 * 解压压缩接口
 * 1. 压缩，解压缩
 * 2. fluent 语法
 * @author binbin.hou
 * @since 0.0.1
 */
public interface Compress {


    /**
     * 压缩
     */
    void compress();

    /**
     * 解压
     */
    void uncompress();

}
