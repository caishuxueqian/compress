package com.github.houbb.compress.handler.impl;

import com.github.houbb.compress.context.ArchiveContext;
import com.github.houbb.compress.context.IContext;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.adaptor.ArchiveHandlerAdaptor;
import com.github.houbb.compress.util.CompressFileUtil;
import com.github.houbb.compress.util.PathUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
abstract class AbstractArchiveHandler extends ArchiveHandlerAdaptor {

    /**
     * 执行归档
     * @param targetFile 文件
     * @param password 密码
     * @return 输出流
     */
    protected abstract ArchiveOutputStream getArchiveOutputStream(final File targetFile,
                                                                  final String password);

    /**
     * 构建归档明细
     * @param archiveOutputStream 输出流
     * @param fileToArchive 归档文件
     * @param entryName 实体明细
     * @return 归档明细
     */
    protected abstract ArchiveEntry getArchiveEntry(final ArchiveOutputStream archiveOutputStream,
                                                    final File fileToArchive, final String entryName);


    @Override
    public void handle(IContext context) {
        this.doHandle((ArchiveContext) context);
    }

    /**
     * 默认的实现方式
     * @param context 上下文
     */
    protected void doHandle(final ArchiveContext context) {
        // 基础信息
        final File targetFile = context.getTargetPath().toFile();
        final List<Path> pathList = buildAllPaths(context.getSourcePaths());
        final Path publicParentPath = PathUtil.getPublicParentPath(context.getSourcePaths());
        final String password = context.getPassword();

        try(ArchiveOutputStream outputStream = this.getArchiveOutputStream(targetFile, password)){
            // 循环添加 entry 进入归档
            for(Path path : pathList) {
                // 文件
                File fileToArchive = path.toFile();
                final String entryName = getEntryName(publicParentPath, fileToArchive, context);
                final ArchiveEntry entry = getArchiveEntry(outputStream, fileToArchive, entryName);

                if(Files.isDirectory(path)) {
                    outputStream.putArchiveEntry(entry);
                } else {
                    final byte[] contentOfEntry = Files.readAllBytes(path);
                    outputStream.putArchiveEntry(entry);
                    outputStream.write(contentOfEntry);
                }
            }
            outputStream.closeArchiveEntry();
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 构建所有的路径信息
     * @param pathList 路径列表
     * @return 路径列表
     */
    private List<Path> buildAllPaths(final List<Path> pathList) {
        List<Path> allPaths = new ArrayList<>();
        for(Path path : pathList) {
            allPaths.addAll(CompressFileUtil.getPathList(path));
        }
        return allPaths;
    }

    /**
     * 获取明细的名称
     * @param publicParentPath 公共的父类路径
     * @param fileToArchive 归档文件
     * @param compressContext 上下文
     * @return 结果
     */
    private String getEntryName(final Path publicParentPath,
                                final File fileToArchive,
                                final ArchiveContext compressContext) {
        if(compressContext.isRelativePath()) {
            return PathUtil.getRelativePath(publicParentPath, fileToArchive.toPath());
        } else {
            // 返回全路径
            return fileToArchive.getPath();
        }
    }

}
