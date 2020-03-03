package com.github.houbb.compress.api;

import com.github.houbb.compress.support.file.IFileInfo;

import java.util.List;

/**
 * 解压结果
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public interface IUncompressResult {

    /**
     * 解压明细列表
     * @return 信息列表
     * @since 0.0.5
     */
    List<IFileInfo> fileInfos();

}
