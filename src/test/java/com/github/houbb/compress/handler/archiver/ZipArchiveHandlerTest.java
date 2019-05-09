package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.impl.DefaultArchiveContext;
import com.github.houbb.compress.context.impl.DefaultUnArchiveContext;
import com.github.houbb.compress.handler.ArchiveHandler;
import com.github.houbb.compress.handler.UnArchiveHandler;
import com.github.houbb.compress.handler.archive.ZipArchiveHandler;
import com.github.houbb.compress.handler.archive.ZipUnArchiveHandler;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ZipArchiveHandlerTest {

    /**
     * 解档压缩文件
     */
    @Test
    public void unArchiveTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\1.zip";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\zip\\";

        UnArchiveHandler handler = new ZipUnArchiveHandler();

        DefaultUnArchiveContext handlerContext = new DefaultUnArchiveContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(zipPath)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        handler.handle(handlerContext);
    }

    /**
     * 使用相对路径归档文件
     */
    @Test
    public void archiveDirRelativePathTest() {
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\zip\\";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\zip\\1.zip";

        ArchiveHandler archiveHandler = new ZipArchiveHandler();

        DefaultArchiveContext handlerContext = new DefaultArchiveContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        handlerContext.setRelativePath(true);

        archiveHandler.handle(handlerContext);
    }

}
