package com.github.houbb.compress.constant.enums;

import com.github.houbb.compress.exception.CompressRuntimeException;

/**
 * 压缩类型枚举
 * @author binbin.hou
 * @since 0.0.3
 */
public enum CompressTypeEnum {

    /**
     * 归档系列
     */
    JAR("jar"),

    /**
     * 7z 压缩方式
     */
    SEVENZ("7z"),

    /**
     * zip 压缩方式
     */
    ZIP("zip"),

    /**
     * tar 压缩方式
     */
    TAR("tar"),
    ;

    /**
     * 文件后缀
     * @since 0.0.4
     */
    private final String fileSuffix;

    CompressTypeEnum(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String fileSuffix() {
        return fileSuffix;
    }

    /**
     * 根据文件后缀获取对应的实现
     * @param fileSuffix 文件后缀
     * @return 压缩类型枚举
     * @since 0.0.4
     */
    public static CompressTypeEnum of(final String fileSuffix) {
        for(CompressTypeEnum typeEnum : CompressTypeEnum.values()) {
            if(typeEnum.fileSuffix.equals(fileSuffix)) {
                return typeEnum;
            }
        }

        throw new CompressRuntimeException("Not support compress type for: " + fileSuffix);
    }

}
