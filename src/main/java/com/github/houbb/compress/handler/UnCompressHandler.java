package com.github.houbb.compress.handler;

import com.github.houbb.compress.api.ICompressContext;

/**
 * 解压缩器接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface UnCompressHandler {

    /**
     * 执行上下文
     * @param context 上下文
     */
    void handle(final ICompressContext context);

}
