package com.github.houbb.compress.handler;

import com.github.houbb.compress.context.CompressContext;
import com.github.houbb.compress.context.UnCompressContext;

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
    void compress(final CompressContext context);

    /**
     * 解压
     * 1. 如果是归档类压缩算法，则目标文件应该是文件夹。
     * 2. 如果是压缩类解压算法，则目标文件为文件。
     * @param context 上下文
     */
    void unCompress(final UnCompressContext context);

}
