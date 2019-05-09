package com.github.houbb.compress.context.impl;

import com.github.houbb.compress.context.ICompressContext;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.nio.file.Path;
import java.util.List;

/**
 * 默认的上下文实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class DefaultCompressContext implements ICompressContext {

    /**
     * 是否使用相对路径进行归档
     */
    private boolean isRelativePath;

    /**
     * 原始文件路径
     */
    private List<Path> sourcePaths;

    /**
     * 目标文件路径
     */
    private Path targetPath;

    /**
     * 密码
     */
    private String password;

    @Override
    public boolean isRelativePath() {
        return isRelativePath;
    }

    /**
     * 设置是否使用相对路径
     * @param relativePath 相对路径
     * @return 本身
     */
    public DefaultCompressContext isRelativePath(boolean relativePath) {
        isRelativePath = relativePath;
        return this;
    }

    public DefaultCompressContext sourcePaths(List<Path> sourcePaths) {
        this.sourcePaths = sourcePaths;
        return this;
    }

    @Override
    public List<Path> sourcePaths() {
        return sourcePaths;
    }

    @Override
    public Path sourcePathFirst() {
        if(CollectionUtil.isEmpty(sourcePaths)) {
            return null;
        }

        return this.sourcePaths.get(0);
    }


    public DefaultCompressContext targetPath(Path targetPath) {
        this.targetPath = targetPath;
        return this;
    }


    @Override
    public Path targetPath() {
        return targetPath;
    }

    public DefaultCompressContext password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String password() {
        return password;
    }

}
