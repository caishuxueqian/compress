package com.github.houbb.compress.handler;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.IUncompressResult;

/**
 * 解压缩器接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IUncompressHandler {

    /**
     * 执行上下文
     * @param context 上下文
     * @return 解压结果
     * @since 0.0.1
     */
    IUncompressResult handle(final ICompressContext context);

}
