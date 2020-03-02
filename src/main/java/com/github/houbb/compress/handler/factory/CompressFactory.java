package com.github.houbb.compress.handler.factory;

import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.handler.ICompressHandler;
import com.github.houbb.compress.handler.archive.JarArchiveHandler;
import com.github.houbb.compress.handler.archive.ServenZArchiveHandler;
import com.github.houbb.compress.handler.archive.TarArchiveHandler;
import com.github.houbb.compress.handler.archive.ZipArchiveHandler;

import java.util.EnumMap;
import java.util.Map;

/**
 * 压缩相关类工厂
 * @author binbin.hou
 * @since 0.0.3
 */
public final class CompressFactory {

    private CompressFactory(){}

    /**
     * 解压缩 map
     * @since 0.0.3
     */
    private static final Map<CompressTypeEnum, ICompressHandler> COMPRESS_MAP = new EnumMap<>(CompressTypeEnum.class);

    static {
        COMPRESS_MAP.put(CompressTypeEnum.JAR, new JarArchiveHandler());
        COMPRESS_MAP.put(CompressTypeEnum.SEVENZ, new ServenZArchiveHandler());
        COMPRESS_MAP.put(CompressTypeEnum.TAR, new TarArchiveHandler());
        COMPRESS_MAP.put(CompressTypeEnum.ZIP, new ZipArchiveHandler());
    }

    /**
     * 获取解压缩实现
     * @param compressTypeEnum 类型
     * @return 结果
     * @since 0.0.3
     */
    public static ICompressHandler getHandler(final CompressTypeEnum compressTypeEnum) {
        return COMPRESS_MAP.get(compressTypeEnum);
    }

}
