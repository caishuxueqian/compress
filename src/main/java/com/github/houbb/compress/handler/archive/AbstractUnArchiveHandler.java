package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.context.ICompressContext;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.adaptor.UnArchiveHandlerAdaptor;
import com.github.houbb.compress.util.CompressFileUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
abstract class AbstractUnArchiveHandler extends UnArchiveHandlerAdaptor {

    /**
     * 获取文件输入流
     * @param sourceFile 原始文件
     * @param password 密码
     * @return 输入流
     */
    protected abstract ArchiveInputStream getArchiveInputStream(final File sourceFile,
                                                                final String password);

    @Override
    public void handle(final ICompressContext context) {
        this.doHandler(context);
    }

    protected void doHandler(final ICompressContext context) {
        final File sourceFile = context.sourcePathFirst().toFile();
        final File targetDir = context.targetPath().toFile();
        final String password = context.password();

        try(ArchiveInputStream inputStream = getArchiveInputStream(sourceFile, password)) {
            ArchiveEntry entry = inputStream.getNextEntry();
            while (entry != null) {
                // 处理文件夹
                if (entry.isDirectory()) {
                    final File dir = buildFile(targetDir, entry);
                    CompressFileUtil.makeDir(dir);
                    entry = inputStream.getNextEntry();
                    continue;
                }

                // 处理单个文件
                // 创建以来的文件夹。
                final File file = buildFile(targetDir, entry);
                file.getParentFile().mkdirs();
                try(FileOutputStream out = new FileOutputStream(file)) {
                    byte[] content = new byte[(int) entry.getSize()];
                    inputStream.read(content, 0, content.length);
                    out.write(content);
                }
                entry = inputStream.getNextEntry();
            }
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
