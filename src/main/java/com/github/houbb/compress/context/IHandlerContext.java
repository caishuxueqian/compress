package com.github.houbb.compress.context;

import java.nio.file.Path;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IHandlerContext {

    /**
     * 原始文件 path 列表
     * @return path 列表
     */
    List<Path> sourcePaths();

    /**
     * 目标文件路径
     * @return 目标文件
     */
    Path targetPath();

    /**
     * 密码
     * @return 加密信息
     */
    String password();


}
