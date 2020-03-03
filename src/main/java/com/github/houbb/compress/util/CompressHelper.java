package com.github.houbb.compress.util;

import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.bs.CompressBs;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.support.result.compress.impl.CompressResultHandlers;
import com.github.houbb.compress.support.result.uncompress.impl.UncompressResultHandlers;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.io.StreamUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p> project: compress-CompressHelper </p>
 * <p> create on 2020/3/2 19:48 </p>
 *
 * @author binbin.hou
 * @since 0.0.4
 */
public final class CompressHelper {

    private CompressHelper() {
    }

    /**
     * 执行文件压缩
     * <p>
     * 1. 默认采用 zip 算法
     * 2. 默认采用待压缩文件所在的路径。
     *
     * @param source 原始文件路径
     * @return 结果
     * @since 0.0.4
     */
    public static ICompressResult compress(final String source) {
        final String target = FileUtil.getDirPath(source) + FileUtil.getFileName(source)
                + PunctuationConst.DOT
                + CompressTypeEnum.ZIP.fileSuffix();

        return compress(CompressTypeEnum.ZIP, source, target);
    }

    /**
     * 执行文件压缩
     * <p>
     * 默认根据目标后缀选择压缩算法。
     *
     * @param source 原始文件路径
     * @param target 目标文件路径
     * @return 结果
     * @since 0.0.4
     */
    public static ICompressResult compress(final String source, final String target) {
        final String fileSuffix = FileUtil.getSuffix(target);
        CompressTypeEnum compressTypeEnum = CompressTypeEnum.of(fileSuffix);

        return compress(compressTypeEnum, source, target);
    }

    /**
     * 执行文件压缩
     *
     * @param compressTypeEnum 压缩类型
     * @param source           原始文件路径
     * @param target           目标文件路径
     * @since 0.0.4
     * @return 结果
     */
    public static ICompressResult compress(final CompressTypeEnum compressTypeEnum,
                                           final String source,
                                           final String target) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");
        ArgUtil.notEmpty(source, "source");
        ArgUtil.notEmpty(target, "target");

        return CompressBs.newInstance(compressTypeEnum)
                .compressSources(source)
                .target(target)
                .compress(CompressResultHandlers.defaults());
    }

    /**
     * 执行文件解压
     *
     * @param compressTypeEnum 压缩类型
     * @param source           原始文件路径
     * @param targetDir        目标文件夹路径
     * @return 结果
     * @since 0.0.4
     */
    public static IUncompressResult uncompress(final CompressTypeEnum compressTypeEnum,
                                               final String source,
                                               final String targetDir) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");
        ArgUtil.notEmpty(source, "source");
        ArgUtil.notEmpty(targetDir, "target");

        try (InputStream inputStream = new FileInputStream(source)) {
            return uncompress(compressTypeEnum, inputStream, targetDir);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 执行文件解压
     *
     * @param compressTypeEnum 压缩类型
     * @param sourceStream     原始文件流
     * @param targetDir        目标文件夹路径
     * @return 结果
     * @since 0.0.4
     */
    public static IUncompressResult uncompress(final CompressTypeEnum compressTypeEnum,
                                  final InputStream sourceStream,
                                  final String targetDir) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");
        ArgUtil.notNull(sourceStream, "sourceStream");
        ArgUtil.notEmpty(targetDir, "target");

        try {
            return CompressBs.newInstance(compressTypeEnum)
                    .uncompressStream(sourceStream)
                    .target(targetDir)
                    .uncompress(UncompressResultHandlers.defaults());
        } finally {
            StreamUtil.closeStream(sourceStream);
        }
    }

    /**
     * 执行文件解压
     *
     * @param source    原始文件路径
     * @param targetDir 目标文件夹路径
     * @return 结果
     * @since 0.0.4
     */
    public static IUncompressResult uncompress(final String source,
                                  final String targetDir) {
        final String fileSuffix = FileUtil.getSuffix(source);

        CompressTypeEnum compressTypeEnum = CompressTypeEnum.of(fileSuffix);
        return uncompress(compressTypeEnum, source, targetDir);
    }

    /**
     * 执行文件解压
     *
     * @param source 原始文件路径
     * @return 结果
     * @since 0.0.4
     */
    public static IUncompressResult uncompress(final String source) {
        final String targetDir = FileUtil.getDirPath(source);
        return uncompress(source, targetDir);
    }

}
