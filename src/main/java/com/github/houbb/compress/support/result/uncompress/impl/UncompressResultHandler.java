package com.github.houbb.compress.support.result.uncompress.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class UncompressResultHandler implements IUncompressResultHandler<IUncompressResult> {

    @Override
    public IUncompressResult handler(IUncompressResult result) {
        return result;
    }

}
