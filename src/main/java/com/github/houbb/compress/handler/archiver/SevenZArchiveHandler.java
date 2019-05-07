package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.CompressContext;
import com.github.houbb.compress.context.IContext;
import com.github.houbb.compress.context.UnCompressContext;
import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.CompressHandlerAdaptor;
import com.github.houbb.compress.util.CompressFileUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 7z 归档实现
 * https://www.jianshu.com/p/ddc514f77a1f
 * TODO: 添加相对文件路径的修复
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SevenZArchiveHandler extends CompressHandlerAdaptor {

    /**
     * 递归处理所有需要归档的文件信息
     * @param context 上下文
     */
    @Override
    public void compress(CompressContext context) {
        final File targetFile = context.getTargetPath().toFile();
        final List<Path> pathList = buildAllPaths(context);

        try(SevenZOutputFile sevenZOutput = new SevenZOutputFile(targetFile)){
            // 循环添加 entry 进入归档
            for(Path path : pathList) {
                File fileToArchive = path.toFile();
                final String entryName = fileToArchive.getName();
                final byte[] contentOfEntry = Files.readAllBytes(path);

                SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(fileToArchive, entryName);
                sevenZOutput.putArchiveEntry(entry);
                sevenZOutput.write(contentOfEntry);
            }
            sevenZOutput.closeArchiveEntry();
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 构建所有的路径信息
     * @param context 上下文
     * @return 路径列表
     */
    private List<Path> buildAllPaths(final IContext context) {
        List<Path> allPaths = new ArrayList<>();
        final List<Path> pathList = context.getSourcePaths();

        for(Path path : pathList) {
            allPaths.addAll(CompressFileUtil.getPathList(path));
        }
        return allPaths;
    }

    @Override
    public void unCompress(UnCompressContext context) {
        final File sourceFile = context.getSourcePathFirst().toFile();
        final File targetDir = context.getTargetPath().toFile();
        final String password = context.getPassword();

        try(SevenZFile sevenZFile = new SevenZFile(sourceFile, password.toCharArray())) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            while (entry != null) {
                // 处理文件夹
                if (entry.isDirectory()) {
                    final File dir = buildFile(targetDir, entry);
                    CompressFileUtil.makeDir(dir);
                    entry = sevenZFile.getNextEntry();
                    continue;
                }

                // 处理单个文件
                final File file = buildFile(targetDir, entry);
                try(FileOutputStream out = new FileOutputStream(file)) {
                    byte[] content = new byte[(int) entry.getSize()];
                    sevenZFile.read(content, 0, content.length);
                    out.write(content);
                }
                entry = sevenZFile.getNextEntry();
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
    private File buildFile(final File targetDir, final SevenZArchiveEntry entry) {
        return new File(targetDir.getPath() + File.separator + entry.getName());
    }

}
