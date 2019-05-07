package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.DefaultCompressContext;
import com.github.houbb.compress.context.DefaultContext;
import com.github.houbb.compress.context.DefaultUnCompressContext;
import com.github.houbb.compress.context.IContext;
import com.github.houbb.compress.handler.CompressHandler;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class TarArchiveHandlerTest {

    @Test
    public void unCompressTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\2.tar";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\";
        CompressHandler compressHandler = new TarArchiveHandler();
        DefaultUnCompressContext handlerContext = new DefaultUnCompressContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(zipPath)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        compressHandler.unCompress(handlerContext);
                ;
        compressHandler.unCompress(handlerContext);
    }

    @Test
    public void compressFilesTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\test.tar";
        final String sourcePath1 = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
        final String sourcePath2 = "C:\\Users\\binbin.hou\\Desktop\\2.txt";

        CompressHandler compressHandler = new TarArchiveHandler();
        DefaultCompressContext handlerContext = new DefaultCompressContext();

        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourcePath1), Paths.get(sourcePath2)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        compressHandler.compress(handlerContext);
    }

    @Test
    public void compressDirTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\1.tar";
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";

        CompressHandler compressHandler = new TarArchiveHandler();

        DefaultCompressContext handlerContext = new DefaultCompressContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        handlerContext.setRelativePath(true);
        compressHandler.compress(handlerContext);
    }

    @Test
    public void compressDirNotRelativeTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\2.tar";
        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";

        CompressHandler compressHandler = new TarArchiveHandler();

        DefaultCompressContext handlerContext = new DefaultCompressContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourceDir)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        compressHandler.compress(handlerContext);
    }

}
