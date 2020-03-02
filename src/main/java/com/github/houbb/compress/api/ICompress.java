package com.github.houbb.compress.api;

import java.io.File;

/**
 * <p> project: compress-ICompress </p>
 * <p> create on 2020/3/2 21:04 </p>
 *
 * @author Administrator
 * @since 1.0.0
 */
public interface ICompress {

    /**
     * 压缩
     * @param compressContext 压缩上下文
     * @since 0.0.4
     * @return 返回压缩的文件信息
     */
    File compress(final ICompressContext compressContext);

    /**
     * 解压缩
     * @param compressContext 上下文
     * @since 0.0.4
     */
    void uncompress(final ICompressContext compressContext);

}
