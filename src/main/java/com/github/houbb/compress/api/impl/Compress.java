package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.ICompress;
import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.handler.factory.CompressFactory;
import com.github.houbb.compress.handler.factory.UnCompressFactory;
import com.github.houbb.heaven.annotation.ThreadSafe;

import java.io.File;

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
    public ICompressResult compress(ICompressContext compressContext) {
        return CompressFactory.getHandler(compressContext.compressType()).handle(compressContext);
    }

    @Override
    public IUncompressResult uncompress(ICompressContext compressContext) {
        return UnCompressFactory.getHandler(compressContext.compressType()).handle(compressContext);
    }

}
