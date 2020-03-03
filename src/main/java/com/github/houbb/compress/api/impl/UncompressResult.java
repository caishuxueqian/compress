package com.github.houbb.compress.api.impl;

import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.support.file.IFileInfo;

import java.util.List;

/**
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public class UncompressResult implements IUncompressResult {

    /**
     * 解压明细列表
     * @since 0.0.5
     *
     */
    private List<IFileInfo> fileInfos;

    /**
     * 新建一个对象实例
     * @return 实例
     * @since 0.0.5
     */
    public static UncompressResult newInstance() {
        return new UncompressResult();
    }

    @Override
    public List<IFileInfo> fileInfos() {
        return fileInfos;
    }

    public UncompressResult fileInfos(List<IFileInfo> fileInfos) {
        this.fileInfos = fileInfos;
        return this;
    }

}
