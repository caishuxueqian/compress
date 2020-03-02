package com.github.houbb.compress.handler.factory;

import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.handler.ICompressHandler;
import com.github.houbb.compress.handler.archive.JarUnArchiveHandler;
import com.github.houbb.compress.handler.archive.ServenZUnArchiveHandler;
import com.github.houbb.compress.handler.archive.TarUnArchiveHandler;
import com.github.houbb.compress.handler.archive.ZipUnArchiveHandler;

import java.util.EnumMap;
import java.util.Map;

/**
 * 压缩相关类工厂
 * @author binbin.hou
 * @since 0.0.3
 */
public final class UnCompressFactory {

    private UnCompressFactory(){}

    /**
     * 解压缩 map
     * @since 0.0.3
     */
    private static final Map<CompressTypeEnum, ICompressHandler> UNCOMPRESS_MAP = new EnumMap<>(CompressTypeEnum.class);

    static {
        UNCOMPRESS_MAP.put(CompressTypeEnum.JAR, new JarUnArchiveHandler());
        UNCOMPRESS_MAP.put(CompressTypeEnum.SEVENZ, new ServenZUnArchiveHandler());
        UNCOMPRESS_MAP.put(CompressTypeEnum.TAR, new TarUnArchiveHandler());
        UNCOMPRESS_MAP.put(CompressTypeEnum.ZIP, new ZipUnArchiveHandler());
    }

    /**
     * 获取解压缩实现
     * @param compressTypeEnum 类型
     * @return 结果
     * @since 0.0.3
     */
    public static ICompressHandler getHandler(final CompressTypeEnum compressTypeEnum) {
        return UNCOMPRESS_MAP.get(compressTypeEnum);
    }

}
