package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.IUncompressHandler;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    protected IUncompressResult doHandler(final ICompressContext context) {
        final InputStream sourceStream = context.uncompressStream();
        final File targetDir = context.targetPath().toFile();
        final String password = context.password();

        try(ArchiveInputStream inputStream = getArchiveInputStream(sourceStream, password)) {
            ArchiveEntry entry = inputStream.getNextEntry();
            while (entry != null) {
                // 处理文件夹
                if (entry.isDirectory()) {
                    final File dir = buildFile(targetDir, entry);
                    dir.mkdirs();
                    entry = inputStream.getNextEntry();
                    continue;
                }

                // 处理单个文件
                // 创建以来的文件夹。
                final File file = buildFile(targetDir, entry);
                file.getParentFile().mkdirs();
                try(FileOutputStream out = new FileOutputStream(file)) {
                    final int entrySize = getEntrySize(entry);
                    //TODO: 这里这种写法可能存在问题。
                    byte[] content = new byte[entrySize];
                    inputStream.read(content, 0, content.length);
                    out.write(content);
                }

                // 可否返回 targetDir+entryName+bytes[] 信息？
                // 这些作为一个对象，避免对于文件的创建。
                entry = inputStream.getNextEntry();
            }

            //TODO: 解压的结果处理
            return null;
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 构建目标文件
     * @param targetDir 目标文件夹
     * @param entry 明细
     * @return 结果
     */
    private File buildFile(final File targetDir, final ArchiveEntry entry) {
        return new File(targetDir.getPath() + File.separator + entry.getName());
    }

}
