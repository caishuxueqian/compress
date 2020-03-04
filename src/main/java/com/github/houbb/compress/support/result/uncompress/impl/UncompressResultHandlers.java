package com.github.houbb.compress.support.result.uncompress.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
public final class UncompressResultHandlers {

    private UncompressResultHandlers(){}

    /**
     * 默认实现
     * @return 实现
     * @since 0.0.5
     */
    public static IUncompressResultHandler<IUncompressResult> defaults() {
        return Instances.singleton(UncompressResultHandler.class);
    }

    /**
     * 默认实现
     * @return 实现
     * @since 0.0.5
     */
    public static IUncompressResultHandler<byte[]> bytesFirst() {
        return Instances.singleton(BytesFirstUncompressResultHandler.class);
    }

    /**
     * 指定编码的字符串实现
     *
     * @param charsetString 字符编码
     * @return 实现
     * @since 0.0.6
     */
    public static IUncompressResultHandler<String> stringFirst(final String charsetString) {
        Charset charset = Charset.forName(charsetString);
        return new StringFirstUncompressResultHandler(charset);
    }

    /**
     * UTF-8 字符串返回实现
     * @return 实现
     * @since 0.0.6
     */
    public static IUncompressResultHandler<String> stringFirst() {
        return new StringFirstUncompressResultHandler(StandardCharsets.UTF_8);
    }

}
