package com.github.houbb.compress.context;

/**
 * 归档器处理器上下文接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ArchiveContext extends IContext {

    /**
     * 归档是否使用相对路径
     * 1. 默认使用相对位置作为 entry 的名称。
     * 2. 解压归档的时候注意：要创建对应的文件路径信息。
     * @return 是否
     */
    boolean isRelativePath();

}
