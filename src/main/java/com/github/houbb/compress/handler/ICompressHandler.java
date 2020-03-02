package com.github.houbb.compress.handler;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.ICompressResult;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ICompressHandler {

    /**
     * 执行上下文
     * @param context 上下文
     * @return 压缩文件信息
     */
    ICompressResult handle(final ICompressContext context);

}
