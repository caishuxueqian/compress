package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.ICompressResult;

import java.io.File;
import java.io.InputStream;

/**
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @see File 不返回文件是因为如果不创建文件，文件信息就无法获取。
 * @see java.io.FileInputStream 不返回流是因为流一旦用户忘记操作，就会导致内存泄漏。
 * @author binbin.hou
 * @since 0.0.5
 */
public class CompressResult implements ICompressResult {

    /**
     * 文件内容
     * @since 0.0.5
     */
    private byte[] bytes;

    /**
     * 目标文件路径
     * @since 0.0.5
     */
    private String targetPath;

    /**
     * 压缩异常
     * @since 0.0.5
     */
    private Exception compressError;

    public static CompressResult newInstance() {
        return new CompressResult();
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }

    public CompressResult bytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    @Override
    public String targetPath() {
        return targetPath;
    }

    public CompressResult targetPath(String targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    @Override
    public Exception compressError() {
        return compressError;
    }

    public CompressResult compressError(Exception compressError) {
        this.compressError = compressError;
        return this;
    }
}
