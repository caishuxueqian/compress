package com.github.houbb.compress.support.file.impl;

import com.github.houbb.compress.support.file.IFileInfo;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.5
 */
public class FileInfo implements IFileInfo {

    /**
     * 文件路径
     * @since 0.0.5
     */
    private String path;

    /**
     * 文件内容
     * @since 0.0.5
     */
    private byte[] content;

    /**
     * 是否为文件夹
     * @since 0.0.5
     */
    private boolean directory;

    /**
     * 新建对象实例
     * @return 实例
     * @since 0.0.5
     */
    public static FileInfo newInstance() {
        return new FileInfo();
    }

    @Override
    public String path() {
        return path;
    }

    public FileInfo path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public byte[] content() {
        return content;
    }

    public FileInfo content(byte[] content) {
        this.content = content;
        return this;
    }

    @Override
    public boolean directory() {
        return directory;
    }

    public FileInfo directory(boolean directory) {
        this.directory = directory;
        return this;
    }

}
