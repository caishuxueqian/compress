package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.impl.DefaultCompressContext;
import com.github.houbb.compress.handler.ArchiveHandler;
import com.github.houbb.compress.handler.UnArchiveHandler;
import com.github.houbb.compress.handler.archive.JarArchiveHandler;
import com.github.houbb.compress.handler.archive.JarUnArchiveHandler;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class JarArchiveHandlerTest {

    /**
     * 解档压缩文件
     */
    @Test
    public void unArchiveTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\jar";

        UnArchiveHandler handler = new JarUnArchiveHandler();

        DefaultCompressContext handlerContext = new DefaultCompressContext();
        handlerContext.sourcePaths(Arrays.asList(Paths.get(zipPath)));
        handlerContext.targetPath(Paths.get(targetPath));
        handler.handle(handlerContext);
    }

    /**
     * 使用相对路径归档文件
     */
    @Test
    public void archiveDirRelativePathTest() {
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";

        ArchiveHandler archiveHandler = new JarArchiveHandler();

        DefaultCompressContext handlerContext = new DefaultCompressContext();
        handlerContext.sourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.targetPath(Paths.get(targetPath));
        handlerContext.isRelativePath(true);

        archiveHandler.handle(handlerContext);
    }

}
