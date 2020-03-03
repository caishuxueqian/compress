package com.github.houbb.compress.support.result.compress.impl;

import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.support.result.compress.ICompressResultHandler;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class CompressResultHandler implements ICompressResultHandler<ICompressResult> {

    /**
     * 结果处理
     *
     * @param result 压缩结果
     * @return 结果
     * @since 0.0.5
     */
    @Override
    public ICompressResult handler(final ICompressResult result) {
        return result;
    }

}
