package com.github.houbb.compress.context;

/**
 * 默认的压缩上下文实现
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class DefaultCompressContext extends DefaultContext implements CompressContext {

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
