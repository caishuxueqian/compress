package com.github.houbb.compress.handler;

import com.github.houbb.compress.api.ICompressContext;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ICompressHandler {

    /**
     * 执行上下文
     * @param context 上下文
     */
    void handle(final ICompressContext context);

}
