package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.impl.DefaultArchiveContext;
import com.github.houbb.compress.context.impl.DefaultUnArchiveContext;
import com.github.houbb.compress.handler.ArchiveHandler;
import com.github.houbb.compress.handler.UnArchiveHandler;
import com.github.houbb.compress.handler.impl.JarArchiveHandler;
import com.github.houbb.compress.handler.impl.JarUnArchiveHandler;
import com.github.houbb.compress.handler.impl.TarArchiveHandler;
import com.github.houbb.compress.handler.impl.TarUnArchiveHandler;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class JarArchiveHandlerTest {

    /**
     * 解档压缩文件
     */
    @Test
    public void unArchiveTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\jar";

        UnArchiveHandler handler = new JarUnArchiveHandler();

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
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";

        ArchiveHandler archiveHandler = new JarArchiveHandler();

        DefaultArchiveContext handlerContext = new DefaultArchiveContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        handlerContext.setRelativePath(true);

        archiveHandler.handle(handlerContext);
    }

}
