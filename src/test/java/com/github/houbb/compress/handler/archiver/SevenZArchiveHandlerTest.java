package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.DefaultCompressContext;
import com.github.houbb.compress.context.DefaultContext;
import com.github.houbb.compress.context.DefaultUnCompressContext;
import com.github.houbb.compress.context.IContext;
import com.github.houbb.compress.handler.CompressHandler;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SevenZArchiveHandlerTest {

    @Test
    public void unCompressTest() {
        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\2.7z";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\";
        CompressHandler compressHandler = new SevenZArchiveHandler();

        DefaultUnCompressContext handlerContext = new DefaultUnCompressContext();
        handlerContext.setSourcePaths(Arrays.asList(Paths.get(zipPath)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        compressHandler.unCompress(handlerContext);
    }

    @Test
    public void compressTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\2.7z";
        final String sourcePath1 = "C:\\Users\\binbin.hou\\Desktop\\1";
//        final String sourcePath2 = "C:\\Users\\binbin.hou\\Desktop\\2.txt";

        CompressHandler compressHandler = new SevenZArchiveHandler();
        DefaultCompressContext handlerContext = new DefaultCompressContext();

        handlerContext.setSourcePaths(Arrays.asList(Paths.get(sourcePath1)));
        handlerContext.setTargetPath(Paths.get(targetPath));
        compressHandler.compress(handlerContext);
    }

}
