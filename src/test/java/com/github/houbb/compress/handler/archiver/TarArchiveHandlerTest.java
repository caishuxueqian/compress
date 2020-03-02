package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.api.impl.CompressContext;
import com.github.houbb.compress.handler.ArchiveHandler;
import com.github.houbb.compress.handler.UnArchiveHandler;
import com.github.houbb.compress.handler.archive.TarArchiveHandler;
import com.github.houbb.compress.handler.archive.TarUnArchiveHandler;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class TarArchiveHandlerTest {

    /**
     * 解档压缩文件
     */
    @Test
    public void unArchiveTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\2.tar";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\";

        UnArchiveHandler handler = new TarUnArchiveHandler();

        CompressContext handlerContext = new CompressContext();
        handlerContext.sourcePaths(Arrays.asList(Paths.get(zipPath)));
        handlerContext.targetPath(Paths.get(targetPath));
        handler.handle(handlerContext);
    }

    /**
     * 使用相对路径归档文件
     */
    @Test
    public void archiveDirRelativePathTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\1.tar";
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";

        ArchiveHandler archiveHandler = new TarArchiveHandler();

        CompressContext handlerContext = new CompressContext();
        handlerContext.sourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.targetPath(Paths.get(targetPath));
        handlerContext.isRelativePath(true);

        archiveHandler.handle(handlerContext);
    }

    /**
     * 使用绝对路径归档文件
     */
    @Test
    public void archiveDirNotRelativeTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\2.tar";
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";

        ArchiveHandler archiveHandler = new TarArchiveHandler();

        CompressContext handlerContext = new CompressContext();
        handlerContext.sourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.targetPath(Paths.get(targetPath));
        handlerContext.isRelativePath(false);

        archiveHandler.handle(handlerContext);
    }

}
