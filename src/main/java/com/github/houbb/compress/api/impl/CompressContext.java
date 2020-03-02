package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * 默认的上下文实现
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class CompressContext implements ICompressContext {

    /**
     * 解压文件流
     * @since 0.0.5
     */
    private InputStream uncompressStream;

    /**
     * 是否使用相对路径进行归档
     */
    private boolean isRelativePath;

    /**
     * 原始文件路径
     */
    private List<Path> sourcePaths;

    /**
     * 目标文件路径
     */
    private Path targetPath;

    /**
     * 密码
     */
    private String password;

    /**
     * 压缩类型
     * @since 0.0.4
     */
    private CompressTypeEnum compressType;

    /**
     * 是否需要创建文件
     * @since 0.0.5
     */
    private boolean createFile;

    /**
     * 新建对象
     * @return 对象
     * @since 0.0.4
     */
    public static CompressContext newInstance() {
        return new CompressContext();
    }

    @Override
    public InputStream uncompressStream() {
        return uncompressStream;
    }

    public CompressContext uncompressStream(InputStream uncompressStream) {
        this.uncompressStream = uncompressStream;
        return this;
    }

    @Override
    public boolean isRelativePath() {
        return isRelativePath;
    }

    /**
     * 设置是否使用相对路径
     * @param relativePath 相对路径
     * @return 本身
     */
    public CompressContext isRelativePath(boolean relativePath) {
        isRelativePath = relativePath;
        return this;
    }

    public CompressContext sourcePaths(List<Path> sourcePaths) {
        this.sourcePaths = sourcePaths;
        return this;
    }

    @Override
    public List<Path> compressSources() {
        return sourcePaths;
    }

    @Override
    public Path sourcePathFirst() {
        if(CollectionUtil.isEmpty(sourcePaths)) {
            return null;
        }

        return this.sourcePaths.get(0);
    }


    public CompressContext targetPath(Path targetPath) {
        this.targetPath = targetPath;
        return this;
    }


    @Override
    public Path targetPath() {
        return targetPath;
    }

    public CompressContext password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public CompressTypeEnum compressType() {
        return compressType;
    }

    public CompressContext compressType(CompressTypeEnum compressType) {
        this.compressType = compressType;
        return this;
    }

    @Override
    public boolean createFile() {
        return createFile;
    }

    public CompressContext createFile(boolean createFile) {
        this.createFile = createFile;
        return this;
    }
}
