package com.github.houbb.compress.util;

import com.github.houbb.compress.bs.CompressBs;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.compress.support.result.uncompress.impl.UncompressResultHandlers;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.io.StreamUtil;

import java.io.InputStream;

/**
 * 基于文件流的信息压缩处理
 * <p>
 * （1）不做文件的创建
 * （2）返回的是字节、字符串
 * <p> project: compress-CompressHelper </p>
 * <p> create on 2020/3/2 19:48 </p>
 *
 * @author binbin.hou
 * @since 0.0.6
 */
public final class CompressStreamHelper {

    private CompressStreamHelper() {
    }

    /**
     * 执行文件解压
     *
     * @param sourcePath     原始文件路径
     * @return 结果
     * @since 0.0.6
     */
    public static String uncompress(final String sourcePath) {
        return uncompress(CompressTypeEnum.ZIP, sourcePath);
    }

    /**
     * 执行文件解压
     *
     * @param compressTypeEnum 压缩类型
     * @param sourcePath     原始文件路径
     * @return 结果
     * @since 0.0.6
     */
    public static String uncompress(final CompressTypeEnum compressTypeEnum,
                                   final String sourcePath) {
        return uncompress(compressTypeEnum, sourcePath, UncompressResultHandlers.stringFirst());
    }

    /**
     * 执行文件解压
     *
     * @param compressTypeEnum 压缩类型
     * @param sourcePath     原始文件路径
     * @param handler          结果处理类
     * @param <R>              泛型
     * @return 结果
     * @since 0.0.6
     */
    public static <R> R uncompress(final CompressTypeEnum compressTypeEnum,
                                   final String sourcePath,
                                   final IUncompressResultHandler<R> handler) {
        ArgUtil.notEmpty(sourcePath, "sourcePath");

        InputStream inputStream = StreamUtil.getInputStream(sourcePath);
        return uncompress(compressTypeEnum, inputStream, handler);
    }

    /**
     * 执行文件解压
     *
     * @param compressTypeEnum 压缩类型
     * @param sourceStream     原始文件流
     * @param handler          结果处理类
     * @param <R>              泛型
     * @return 结果
     * @since 0.0.6
     */
    public static <R> R uncompress(final CompressTypeEnum compressTypeEnum,
                                   final InputStream sourceStream,
                                   final IUncompressResultHandler<R> handler) {
        ArgUtil.notNull(compressTypeEnum, "compressTypeEnum");
        ArgUtil.notNull(sourceStream, "sourceStream");
        ArgUtil.notNull(handler, "handler");

        try {
            return CompressBs.newInstance(compressTypeEnum)
                    .uncompressStream(sourceStream)
                    .target(PunctuationConst.SLASH)
                    .createFile(false)
                    .uncompress(handler);
        } finally {
            StreamUtil.closeStream(sourceStream);
        }
    }

}
