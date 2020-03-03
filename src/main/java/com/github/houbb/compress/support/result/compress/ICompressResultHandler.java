package com.github.houbb.compress.support.result.compress;

import com.github.houbb.compress.api.ICompressResult;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
public interface ICompressResultHandler<R> {

    /**
     * 结果处理
     * @param result 压缩结果
     * @return 结果
     * @since 0.0.1
     */
    R handler(final ICompressResult result);

}
