package com.github.houbb.compress.api;

import java.io.File;

/**
 * 压缩结果
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public interface ICompressResult {

    /**
     * 文件信息
     * @since 0.0.5
     * @return 文件信息
     */
    File file();

}
