package com.github.houbb.compress.core;

/**
 * 压缩引导类
 * 1. 如果是文件夹，是否递归压缩/解压。
 * @author binbin.hou
 * @since 0.0.1
 */
public class CompressBootstrap implements Compress {

    private CompressBootstrap(){}

    /**
     * 原始文件路径
     */
    private String origin;

    /**
     * 目标文件路径
     * 1. 默认和原始文件保持一致
     */
    private String target;

    /**
     * 加密密码
     */
    private String secret;

    /**
     * 创建一个实例
     * @return 实例
     */
    public static CompressBootstrap newInstance() {
        return new CompressBootstrap();
    }

    /**
     * 指定路径
     * @param path 路径
     * @return 引导类本身
     */
    public CompressBootstrap origin(final String path) {
        this.origin = path;
        return this;
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
     * 指定目标文件
     * @param secret 路径
     * @return 引导类本身
     */
    public CompressBootstrap secret(final String secret) {
        this.secret = secret;
        return this;
    }

    @Override
    public void compress() {

    }

    @Override
    public void uncompress() {

    }

}
