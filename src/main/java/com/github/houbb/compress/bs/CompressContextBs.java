package com.github.houbb.compress.bs;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 压缩上下文引导类
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class CompressContextBs implements ICompressContext {

    /**
     * 原始文件
     * 1. 允许指定多个文件。
     * 2. 允许重复
     */
    private List<Path> sourcePaths = new ArrayList<>();

    /**
     * 目标文件路径
     * 1. 默认和原始文件的第一个保持一致
     *
     * 归档：
     * （1） 如果原始文件为文件夹，则归档结果为 dirName.archiveType
     * （2） 如果原始文件为文件，则归档结果为 fileName.archiveType
     *
     * 解归档
     * （1） 原始文件的父文件夹下，建立一个文件夹和压缩文件的 name 相同的 dir，放置解压文件。
     */
    private Path targetPath;

    /**
     * 加密密码
     * 1. 对于不支持加密的归档/解档算法，如果设置这个值，则直接忽略。
     * 2. 默认不使用密匙
     */
    private String password = null;

    /**
     * 是否使用相对路径
     * 1. 默认为真
     */
    private boolean isRelativePath = true;

    /**
     * 压缩算法
     * @since 0.0.4
     */
    private CompressTypeEnum compressType = CompressTypeEnum.ZIP;

    private CompressContextBs(){}

    /**
     * 创建新实例
     * @return this
     */
    public static CompressContextBs newInstance() {
        return new CompressContextBs();
    }

    /**
     * @param sourcePaths 待处理文件路径
     * @return  引导类本身
     */
    public CompressContextBs source(String ... sourcePaths) {
        this.sources(sourcePaths);
        return this;
    }

    /**
     * 指定目标路径
     * @param targetPath 目标路径
     * @return  引导类本身
     */
    public CompressContextBs target(final String targetPath) {
        //1. 待处理文件路径禁止为空。
        if(StringUtil.isEmpty(targetPath)) {
            throw new CompressRuntimeException("Target path not allow empty!");
        }

        this.targetPath = Paths.get(targetPath);
        return this;
    }

    /**
     * 是否使用相对路径
     * 1. 仅仅作用于归档实现类。
     * 2. 解归档如果设置，则直接忽略
     * @param isRelativePath 是否使用相对路径
     * @return 引导类本身
     */
    public CompressContextBs isRelativePath(final boolean isRelativePath) {
        this.isRelativePath = isRelativePath;
        return this;
    }

    /**
     * 设置密码
     * 1. 如果不支持的类，设置这个值则直接忽略。
     * @param password 密码
     * @return 引导类本身
     */
    public CompressContextBs password(final String password) {
        this.password = password;
        return this;
    }


    @Override
    public List<Path> sourcePaths() {
        return this.sourcePaths;
    }

    @Override
    public Path sourcePathFirst() {
        if(CollectionUtil.isEmpty(sourcePaths)) {
            return null;
        }
        return sourcePaths.get(0);
    }

    @Override
    public Path targetPath() {
        return this.targetPath;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public boolean isRelativePath() {
        return isRelativePath;
    }

    @Override
    public CompressTypeEnum compressType() {
        return compressType;
    }

    public CompressContextBs compressType(CompressTypeEnum compressType) {
        this.compressType = compressType;
        return this;
    }

    /**
     * @param sourcePaths 待处理文件路径
     * @return  引导类本身
     */
    private CompressContextBs sources(String ... sourcePaths) {
        //1. 待处理文件路径禁止为空。
        if(ArrayUtil.isEmpty(sourcePaths)) {
            throw new CompressRuntimeException("Source paths not allow empty!");
        }

        //2. 添加文件路径信息
        for(String source : sourcePaths) {
            this.sourcePaths.add(Paths.get(source));
        }

        return this;
    }
}
