package com.github.houbb.compress.support.result.compress.impl;

import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.support.result.compress.ICompressResultHandler;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
public final class CompressResultHandlers {

    private CompressResultHandlers(){}

    /**
     * 默认实现
     * @return 实现
     * @since 0.0.5
     */
    public static ICompressResultHandler<ICompressResult> defaults() {
        return Instances.singleton(CompressResultHandler.class);
    }

}
