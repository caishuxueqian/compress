package com.github.houbb.compress.support.result.uncompress.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
public final class UncompressResultHandlers {

    private UncompressResultHandlers(){}

    /**
     * 默认实现
     * @return 实现
     * @since 0.0.5
     */
    public static IUncompressResultHandler<IUncompressResult> defaults() {
        return Instances.singleton(UncompressResultHandler.class);
    }

}
