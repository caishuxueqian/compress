package com.github.houbb.compress.api;

import com.github.houbb.compress.support.file.IFileInfo;

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
     * 文件信息
     * @return 信息
     * @since 0.0.5
     */
    IFileInfo fileInfo();

    /**
     * 移除异常
     * （1）只有在不创建文件时会做删除处理。
     * @return 移除异常
     * @since 0.0.5
     */
    Exception compressError();

}
