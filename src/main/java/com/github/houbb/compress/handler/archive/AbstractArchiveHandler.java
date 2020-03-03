package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.api.ICompressContext;
import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.api.impl.CompressResult;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.ICompressHandler;
import com.github.houbb.heaven.support.condition.ICondition;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.nio.PathUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
abstract class AbstractArchiveHandler implements ICompressHandler {

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
    public ICompressResult handle(ICompressContext context) {
        return this.doHandle(context);
    }

    /**
     * 默认的实现方式
     * @param context 上下文
     */
    protected ICompressResult doHandle(final ICompressContext context) {
        // 基础信息
        final String targetPath = context.targetPath().toString();
        final File targetFile = context.targetPath().toFile();
        final List<Path> pathList = buildAllPaths(context.compressSources());
        final Path publicParentPath = PathUtil.getPublicParentPath(context.compressSources());
        final String password = context.password();

        CompressResult compressResult = CompressResult.newInstance();
        try(ArchiveOutputStream outputStream = this.getArchiveOutputStream(targetFile, password)){
            // 循环添加 entry 进入归档
            ArchiveEntry entry = null;
            for(Path path : pathList) {
                // 文件
                File fileToArchive = path.toFile();
                final String entryName = getEntryName(publicParentPath, fileToArchive, context);
                entry = getArchiveEntry(outputStream, fileToArchive, entryName);

                if(Files.isDirectory(path)) {
                    outputStream.putArchiveEntry(entry);
                } else if(fileToArchive.isFile()) {
                    final byte[] contentOfEntry = Files.readAllBytes(path);
                    outputStream.putArchiveEntry(entry);
                    outputStream.write(contentOfEntry);
                } else {
                    //ignore
                }
            }
            // 判空关闭处理 @since 0.0.3`
            if(entry != null) {
                outputStream.closeArchiveEntry();
            }

            byte[] bytes = FileUtil.getFileBytes(targetFile);
            return compressResult.bytes(bytes)
                    .targetPath(targetPath);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        } finally {
            if(!context.createFile()) {
                // 移除文件
                try {
                    Files.delete(targetFile.toPath());
                } catch (IOException e) {
                    // 记录下删除失败的异常
                    compressResult.compressError(e);
                }
            }
        }
    }

    /**
     * 构建所有的路径信息
     * @param pathList 路径列表
     * @return 路径列表
     */
    private List<Path> buildAllPaths(final List<Path> pathList) {
        List<Path> resultPaths = new ArrayList<>();
        List<Path> allPaths = new ArrayList<>();
        for(Path path : pathList) {
            allPaths.addAll(PathUtil.getPathList(path));
        }

        //1. 排序一下，文件夹放在前面。文件放在后面，不然会出现先创建建文件，再创建文件夹的错误问题。
        //TODO: 实际对于 jar 好像这么做也没有用。后期优化掉。
        List<Path> dirList = CollectionUtil.conditionList(allPaths, new ICondition<Path>() {
            @Override
            public boolean condition(Path path) {
                return Files.isDirectory(path);
            }
        });

        List<Path> fileList = CollectionUtil.conditionList(allPaths, new ICondition<Path>() {
            @Override
            public boolean condition(Path path) {
                return path.toFile().isFile();
            }
        });
        resultPaths.addAll(dirList);
        resultPaths.addAll(fileList);

        return resultPaths;
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
                                final ICompressContext compressContext) {
        if(compressContext.relativePath()) {
            return PathUtil.getRelativePath(publicParentPath, fileToArchive.toPath());
        } else {
            // 返回全路径
            return fileToArchive.getPath();
        }
    }

}
