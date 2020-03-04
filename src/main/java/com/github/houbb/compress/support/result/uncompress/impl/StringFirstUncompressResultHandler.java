package com.github.houbb.compress.support.result.uncompress.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.file.IFileInfo;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class StringFirstUncompressResultHandler implements IUncompressResultHandler<String> {

    /**
     * 文件编码
     * @since 0.0.8
     */
    private final Charset charset;

    public StringFirstUncompressResultHandler(Charset charset) {
        this.charset = charset;
    }

    @Override
    public String handler(IUncompressResult result) {
        List<IFileInfo> fileInfoList = result.fileInfos();

        if(CollectionUtil.isEmpty(fileInfoList)) {
            return StringUtil.EMPTY;
        }

        byte[] bytes = fileInfoList.get(0).content();
        return new String(bytes, charset);
    }

}
