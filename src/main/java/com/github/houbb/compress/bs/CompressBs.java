package com.github.houbb.compress.bs;

import com.github.houbb.compress.constant.CompressTypeEnum;
import com.github.houbb.compress.handler.factory.CompressFactory;
import com.github.houbb.compress.handler.factory.UnCompressFactory;
import com.github.houbb.heaven.util.common.ArgUtil;

/**
 * 压缩引导类
 * @author binbin.hou
 * @since 0.0.3
 */
public final class CompressBs {

    private CompressBs(){}

    /**
     * 压缩算法类型
     * @since 0.0.3
     */
    private CompressTypeEnum compressTypeEnum;

    /**
     * 压缩上下文引导类
     * @since 0.0.3
     */
    private CompressContextBs compressContextBs = CompressContextBs.newInstance();

    /**
     * 指定压缩算法
     * @param compressTypeEnum 压缩算法类型
     * @return this
     */
    public static CompressBs newInstance(final CompressTypeEnum compressTypeEnum) {
        CompressBs compressBs = new CompressBs();
        return compressBs.compressTypeEnum(compressTypeEnum);
    }

    /**
     * 指定压缩类型
     * @param compressTypeEnum 压缩类型
     * @return this
     */
    private CompressBs compressTypeEnum(final CompressTypeEnum compressTypeEnum) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");
        this.compressTypeEnum = compressTypeEnum;
        return this;
    }

    /**
     * 指定原始文件路径
     * @param source 原始文件路径
     * @return this
     */
    public CompressBs source(String source) {
        ArgUtil.notEmpty(source, "source");
        compressContextBs.source(source);
        return this;
    }

    /**
     * 指定目标文件路径
     * @param target 目标文件路径
     * @return this
     */
    public CompressBs target(String target) {
        ArgUtil.notEmpty(target, "target");
        compressContextBs.target(target);
        return this;
    }

    /**
     * 指定密码
     * @param password 密码
     * @return this
     */
    public CompressBs password(String password) {
        ArgUtil.notEmpty(password, "password");
        compressContextBs.password(password);
        return this;
    }

    /**
     * 指定是否使用相对路径
     * @param relativePath 是否为相对路径
     * @return this
     */
    public CompressBs relativePath(boolean relativePath) {
        compressContextBs.isRelativePath(relativePath);
        return this;
    }

    /**
     * 压缩
     */
    public void compress() {
        CompressFactory.getHandler(this.compressTypeEnum).handle(this.compressContextBs);
    }

    /**
     * 解压缩
     */
    public void uncompress() {
        UnCompressFactory.getHandler(this.compressTypeEnum).handle(this.compressContextBs);
    }

}
