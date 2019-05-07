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
     * @param path 文件路径
     * @param paths 额外文件路径（仅供归档类使用）
     */
    void compress(final String path, final String ... paths);

    /**
     * 解压
     * @param path 文件路径
     */
    void unCompress(final String path);

}
