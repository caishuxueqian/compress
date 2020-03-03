//package com.github.houbb.compress.handler.archiver;
//
//import com.github.houbb.compress.api.impl.CompressContext;
//import com.github.houbb.compress.bs.CompressContextBs;
//import com.github.houbb.compress.handler.archive.JarArchiveHandler;
//import com.github.houbb.compress.handler.archive.JarUnArchiveHandler;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.nio.file.Paths;
//import java.util.Arrays;
//
///**
// * @author binbin.hou
// * @since 0.0.1
// */
//@Ignore
//public class JarArchiveHandlerTest {
//
//    /**
//     * 解档压缩文件
//     */
//    @Test
//    public void unArchiveTest() throws FileNotFoundException {
//        final String zipPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";
//        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\jar";
//
//        JarUnArchiveHandler handler = new JarUnArchiveHandler();
//
//        CompressContext handlerContext = new CompressContext();
//        handlerContext.sourcePaths(Arrays.asList(Paths.get(zipPath)));
//        handlerContext.targetPath(Paths.get(targetPath));
//
//        CompressContextBs.newInstance().uncompressStream(new FileInputStream(zipPath))
//        handler.handle(handlerContext);
//    }
//
//    /**
//     * 使用相对路径归档文件
//     */
//    @Test
//    public void archiveDirRelativePathTest() {
//        final String sourceDir = "C:\\Users\\binbin.hou\\Desktop\\1\\";
//        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\1.jar";
//
//        JarArchiveHandler archiveHandler = new JarArchiveHandler();
//
//        CompressContext handlerContext = new CompressContext();
//        handlerContext.sourcePaths(Arrays.asList(Paths.get(sourceDir)));
//        handlerContext.targetPath(Paths.get(targetPath));
//        handlerContext.relativePath(true);
//
//        archiveHandler.handle(handlerContext);
//    }
//
//}
