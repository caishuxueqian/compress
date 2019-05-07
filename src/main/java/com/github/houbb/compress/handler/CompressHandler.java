package com.github.houbb.compress.handler;

import com.github.houbb.compress.context.IHandlerContext;

/**
 * 压缩器
 * @author binbin.hou
 * @since 0.0.1
 */
public interface CompressHandler extends IHandler {

    /**
     * 压缩
     * @param context 上下文
     */
    void uncompress(final IHandlerContext context);

}
