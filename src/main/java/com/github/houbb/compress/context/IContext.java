package com.github.houbb.compress.context;

import java.nio.file.Path;
import java.util.List;

/**
 * 上下文接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IContext {

    /**
     * 原始文件 path 列表
     * @return path 列表
     */
    List<Path> getSourcePaths();

    /**
     * 获取第一个原始文件 path
     * @return path 文件信息
     */
    Path getSourcePathFirst();

    /**
     * 目标文件路径
     * @return 目标文件
     */
    Path getTargetPath();

    /**
     * 密码
     * @return 加密信息
     */
    String getPassword();

}
