package com.github.houbb.compress.api;

/**
 * 压缩结果
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public interface ICompressResult {

    /**
     * 文件输入流
     * @return 输入流
     * @since 0.0.5
     */
    byte[] bytes();

    /**
     * 目标路径
     * @return 目标路径
     * @since 0.0.5
     */
    String targetPath();

    /**
     * 移除异常
     * （1）只有在不创建文件时会做删除处理。
     * @return 移除异常
     * @since 0.0.5
     */
    Exception compressError();

}
