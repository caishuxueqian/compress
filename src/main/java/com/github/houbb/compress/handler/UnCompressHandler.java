package com.github.houbb.compress.handler;

import com.github.houbb.compress.context.IHandlerContext;

/**
 * 解压器
 * @author binbin.hou
 * @since 0.0.1
 */
public interface UnCompressHandler extends IHandler {

    /**
     * 解压
     * @param context 上下文
     */
    void unCompress(final IHandlerContext context);

}
