package com.github.houbb.compress.context.impl;

import com.github.houbb.compress.context.ArchiveContext;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class DefaultArchiveContext extends DefaultContext implements ArchiveContext {

    /**
     * 是否使用相对路径进行归档
     */
    private boolean isRelativePath;

    @Override
    public boolean isRelativePath() {
        return isRelativePath;
    }

    public void setRelativePath(boolean relativePath) {
        isRelativePath = relativePath;
    }

}
