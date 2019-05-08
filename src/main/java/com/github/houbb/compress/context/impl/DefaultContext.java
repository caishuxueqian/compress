package com.github.houbb.compress.context.impl;

import com.github.houbb.compress.context.IContext;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认的上下文实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class DefaultContext implements IContext {

    /**
     * 原始文件路径
     */
    private List<Path> sourcePaths = new ArrayList<>(8);

    /**
     * 目标文件路径
     */
    private Path targetPath;

    /**
     * 密码
     */
    private String password;

    @Override
    public List<Path> getSourcePaths() {
        return sourcePaths;
    }

    @Override
    public Path getSourcePathFirst() {
        return this.sourcePaths.get(0);
    }

    public void setSourcePaths(List<Path> sourcePaths) {
        this.sourcePaths = sourcePaths;
    }

    @Override
    public Path getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
