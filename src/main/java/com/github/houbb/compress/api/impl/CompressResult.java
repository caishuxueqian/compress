package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.ICompressResult;

import java.io.File;

/**
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public class CompressResult implements ICompressResult {

    /**
     * 文件信息
     * @since 0.0.5
     */
    private File file;

    public static CompressResult newInstance() {
        return new CompressResult();
    }

    @Override
    public File file() {
        return file;
    }

    public CompressResult file(File file) {
        this.file = file;
        return this;
    }
}
