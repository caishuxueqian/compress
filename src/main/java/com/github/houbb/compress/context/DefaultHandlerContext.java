package com.github.houbb.compress.context;

import java.nio.file.Path;
import java.util.List;

/**
 * 默认的上线文实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class DefaultHandlerContext implements IHandlerContext {

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

    public DefaultHandlerContext sourcePaths(List<Path> pathList) {
        this.sourcePaths = pathList;
        return this;
    }

    public DefaultHandlerContext targetPath(Path targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public DefaultHandlerContext password(final String password) {
        this.password = password;
        return this;
    }

    @Override
    public List<Path> sourcePaths() {
        return sourcePaths;
    }

    @Override
    public Path targetPath() {
        return targetPath;
    }

    @Override
    public String password() {
        return password;
    }

}
