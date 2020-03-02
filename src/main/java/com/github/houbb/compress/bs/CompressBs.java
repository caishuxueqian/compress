package com.github.houbb.compress.bs;

import com.github.houbb.compress.api.ICompress;
import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.api.impl.Compress;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;

import java.io.File;
import java.io.InputStream;

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
    private CompressTypeEnum compressType;

    /**
     * 压缩原始文件
     * @since 0.0.4
     */
    private String[] compressSources;

    /**
     * 目标文件路径
     * @since 0.0.4
     */
    private String target;

    /**
     * 加密密码
     * @since 0.0.4
     */
    private String password = null;

    /**
     * 是否使用相对路径
     * @since 0.0.4
     */
    private boolean relativePath = true;

    /**
     * 是否需要创建文件
     * @since 0.0.5
     */
    private boolean createFile = true;

    /**
     * 压缩类实现
     * @since 0.0.4
     */
    private ICompress compress = Instances.singleton(Compress.class);

    /**
     * 解压流
     * @since 0.0.5
     */
    private InputStream uncompressStream;

    /**
     * 指定压缩算法
     * @param compressTypeEnum 压缩算法类型
     * @return this
     * @since 0.0.1
     */
    public static CompressBs newInstance(final CompressTypeEnum compressTypeEnum) {
        CompressBs compressBs = new CompressBs();
        compressBs.compressType = compressTypeEnum;
        return compressBs;
    }

    /**
     * 指定压缩类型
     * @param compressTypeEnum 压缩类型
     * @return this
     * @since 0.0.1
     */
    private CompressBs compressType(final CompressTypeEnum compressTypeEnum) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");

        this.compressType = compressTypeEnum;
        return this;
    }

    /**
     * 指定原始文件路径
     * @param sources 原始文件路径
     * @return this
     * @since 0.0.1
     */
    public CompressBs compressSources(String ...sources) {
        ArgUtil.notEmpty(sources, "sources");

        this.compressSources = sources;
        return this;
    }

    /**
     * 设置解压缩文件流
     * @param uncompressStream 解压文件流
     * @return this
     * @since 0.0.5
     */
    public CompressBs uncompressStream(InputStream uncompressStream) {
        ArgUtil.notNull(uncompressStream, "uncompressStream");

        this.uncompressStream = uncompressStream;
        return this;
    }

    /**
     * 指定目标文件路径
     * @param target 目标文件路径
     * @return this
     * @since 0.0.1
     */
    public CompressBs target(String target) {
        ArgUtil.notEmpty(target, "target");

        this.target = target;
        return this;
    }

    /**
     * 指定密码
     * @param password 密码
     * @return this
     * @since 0.0.1
     */
    public CompressBs password(String password) {
        ArgUtil.notEmpty(password, "password");

        this.password = password;
        return this;
    }

    /**
     * 指定是否使用相对路径
     * @param relativePath 是否为相对路径
     * @return this
     */
    public CompressBs relativePath(boolean relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    /**
     * 设置是否创建文件
     * @param createFile 是否
     * @return this
     * @since 0.0.5
     */
    public CompressBs createFile(boolean createFile) {
        this.createFile = createFile;
        return this;
    }

    /**
     * 压缩
     * @since 0.0.1
     */
    public ICompressResult compress() {
        final ICompressContext context = buildCompressContextBs();
        return compress.compress(context);
    }

    /**
     * 解压缩
     * @since 0.0.1
     */
    public void uncompress() {
        final CompressContextBs context = buildCompressContextBs();
        context.uncompressStream();
        compress.uncompress(context);
    }

    /**
     * 构建压缩上下文引导类
     * @return 引导类
     * @since 0.0.4
     */
    private CompressContextBs buildCompressContextBs() {
        return CompressContextBs.newInstance()
                .isRelativePath(relativePath)
                .compressSources(compressSources)
                .target(target)
                .compressType(compressType)
                .password(password)
                .uncompressStream(uncompressStream)
                .createFile(createFile)
                ;
    }

}
