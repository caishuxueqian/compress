package com.github.houbb.compress.support.file;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.5
 */
public interface IFileInfo {

    /**
     * 文件路径
     * @return 文件路径
     * @since 0.0.5
     */
    String path();

    /**
     * 文件内容
     * @return 内容
     * @since 0.0.5
     */
    byte[] content();

    /**
     * 是否为文件夹
     * @return 是否为文件夹
     * @since 0.0.1
     */
    boolean directory();

    /**
     * 子元素列表
     * @return 元素列表
     * @since 0.0.5
     */
    @Deprecated
    List<IFileInfo> children();

}
