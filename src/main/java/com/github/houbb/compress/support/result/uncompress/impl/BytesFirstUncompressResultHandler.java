package com.github.houbb.compress.support.result.uncompress.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.file.IFileInfo;
import com.github.houbb.compress.support.result.uncompress.IUncompressResultHandler;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.util.List;

/**
 * 结果处理类接口
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class BytesFirstUncompressResultHandler implements IUncompressResultHandler<byte[]> {

    @Override
    public byte[] handler(IUncompressResult result) {
        List<IFileInfo> fileInfoList = result.fileInfos();

        if(CollectionUtil.isEmpty(fileInfoList)) {
            return ArrayPrimitiveUtil.BYTE_EMPTY;
        }

        return fileInfoList.get(0).content();
    }

}
