package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.api.impl.UncompressResult;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.IUncompressHandler;
import com.github.houbb.compress.support.file.IFileInfo;
import com.github.houbb.compress.support.file.impl.FileInfo;
import com.github.houbb.heaven.util.guava.Guavas;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
                    final int entrySize = getEntrySize(entry);
                    byte[] content = new byte[entrySize];
                    //这里读取可能不准，后续可以修正
                    inputStream.read(content, 0, entrySize);
                    fileInfo.content(content);

                    if(createFile) {
                        final File file = new File(path);
                        file.getParentFile().mkdirs();
                        try(FileOutputStream out = new FileOutputStream(file)) {
                            out.write(content);
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
     * 构建目标文件
     * @param targetDir 目标文件夹
     * @param entry 明细
     * @return 结果
     * @since 0.0.1
     */
    @Deprecated
    private File buildFile(final File targetDir, final ArchiveEntry entry) {
        final String path = buildFilePath(targetDir, entry);
        return new File(path);
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
