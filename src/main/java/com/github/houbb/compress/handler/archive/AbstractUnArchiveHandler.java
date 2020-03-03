package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.api.impl.UncompressResult;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.IUncompressHandler;
import com.github.houbb.compress.support.file.IFileInfo;
import com.github.houbb.compress.support.file.impl.FileInfo;
import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.util.guava.Guavas;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.*;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
abstract class AbstractUnArchiveHandler implements IUncompressHandler {

    /**
     * 获取文件输入流
     * @param sourceStream 文件流
     * @param password 密码
     * @return 输入流
     */
    protected abstract ArchiveInputStream getArchiveInputStream(final InputStream sourceStream,
                                                                final String password);

    /**
     * 获取 entry 的大小
     * @param entry 压缩明细
     * @return 结果
     * @since 0.0.4
     */
    protected int getEntrySize(final ArchiveEntry entry) {
        return (int) entry.getSize();
    }

    @Override
    public IUncompressResult handle(final ICompressContext context) {
        return this.doHandler(context);
    }

    /**
     * 执行处理
     * @param context 上下文
     * @return 结果
     * @since 0.0.5
     */
    protected IUncompressResult doHandler(final ICompressContext context) {
        final InputStream sourceStream = context.uncompressStream();
        final File targetDir = context.targetPath().toFile();
        final String password = context.password();
        final boolean createFile = context.createFile();

        final UncompressResult uncompressResult = UncompressResult.newInstance();
        List<IFileInfo> fileInfos = Guavas.newArrayList();
        try(ArchiveInputStream inputStream = getArchiveInputStream(sourceStream, password)) {
            ArchiveEntry entry = inputStream.getNextEntry();
            while (entry != null) {
                final String path = buildFilePath(targetDir, entry);
                FileInfo fileInfo = FileInfo.newInstance().path(path);

                // 处理文件夹
                if (entry.isDirectory()) {
                    // 创建文件
                    if(createFile) {
                        final File dir = new File(path);
                        dir.mkdirs();
                    }
                    // 设置为文件夹
                    fileInfo.directory(true);
                } else {
                    // 处理文件
                    byte[] bytes = inputStreamToBytes(inputStream);
                    fileInfo.content(bytes);

                    if(createFile) {
                        final File file = new File(path);
                        file.getParentFile().mkdirs();
                        try(FileOutputStream out = new FileOutputStream(file)) {
                            out.write(bytes);
                        }
                    }
                }

                fileInfos.add(fileInfo);
                entry = inputStream.getNextEntry();
            }

            // 最后返回结果
            uncompressResult.fileInfos(fileInfos);
            return uncompressResult;
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 输入流转为字节流
     * @param inputStream 输入流
     * @return 字节数组
     * @since 0.0.5
     */
    @CommonEager
    public static byte[] inputStreamToBytes(final InputStream inputStream) {
        try(ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }

            return output.toByteArray();
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 构建文件路径
     * @param targetDir 目标文件夹
     * @param entry 明细
     * @return 结果
     * @since 0.0.5
     */
    private String buildFilePath(final File targetDir, final ArchiveEntry entry) {
        return targetDir.getPath() + File.separator + entry.getName();
    }

}
