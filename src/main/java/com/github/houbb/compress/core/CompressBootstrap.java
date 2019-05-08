package com.github.houbb.compress.core;

import com.github.houbb.compress.handler.IHandler;

/**
 * 压缩引导类
 * 1. 如果是文件夹，是否递归压缩/解压。
 * @author binbin.hou
 * @since 0.0.1
 */
public class CompressBootstrap{

    private CompressBootstrap(){}

    /**
     * 目标文件路径
     * 1. 默认和原始文件保持一致
     */
    private String target;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 创建一个实例
     * @return 实例
     */
    public static CompressBootstrap newInstance() {
        return new CompressBootstrap();
    }

    /**
     * 指定目标文件
     * @param target 路径
     * @return 引导类本身
     */
    public CompressBootstrap target(final String target) {
        this.target = target;
        return this;
    }

    /**
     * 指定密码
     * @param password 密码
     * @return 引导类本身
     */
    public CompressBootstrap password(final String password) {
        this.password = password;
        return this;
    }

    /**
     * 接口的处理
     * 1. 所有的 handler 内部应该交给 handlerPipeline 处理.
     * @param handler 第一个处理器
     * @param handlers 其他的处理器
     * @return 引导类本身
     */
    public CompressBootstrap handler(final IHandler handler, final IHandler handlers) {
        return this;
    }

}
