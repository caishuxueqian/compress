package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.ICompress;
import com.github.houbb.compress.context.ICompressContext;
import com.github.houbb.compress.handler.factory.CompressFactory;
import com.github.houbb.compress.handler.factory.UnCompressFactory;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class Compress implements ICompress {

    @Override
    public void compress(ICompressContext compressContext) {
        CompressFactory.getHandler(compressContext.compressType()).handle(compressContext);
    }

    @Override
    public void uncompress(ICompressContext compressContext) {
        UnCompressFactory.getHandler(compressContext.compressType()).handle(compressContext);
    }

}
