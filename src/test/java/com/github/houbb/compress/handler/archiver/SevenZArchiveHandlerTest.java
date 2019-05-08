package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.context.impl.DefaultArchiveContext;
import com.github.houbb.compress.context.impl.DefaultUnArchiveContext;
import com.github.houbb.compress.handler.impl.ServenZArchiveHandler;
import com.github.houbb.compress.handler.impl.ServenZUnArchiveHandler;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * [案例](https://memorynotfound.com/java-7z-seven-zip-example-compress-decompress-file/)
 * @author binbin.hou
 * @since 0.0.1
 */
public class SevenZArchiveHandlerTest {

    @Test
    public void archiveFileRelativePathTest() {
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\7zFileRelative.7z";

        DefaultArchiveContext context = new DefaultArchiveContext();
        context.setSourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.setTargetPath(Paths.get(targetPath));
        context.setRelativePath(true);

        new ServenZArchiveHandler().handle(context);
    }

    @Test
    public void archiveDirNotRelativePathTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\notRelativeDir.7z";
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1";

        DefaultArchiveContext context = new DefaultArchiveContext();
        context.setSourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.setTargetPath(Paths.get(targetPath));
        context.setRelativePath(false);

        new ServenZArchiveHandler().handle(context);
    }

    @Test
    public void unArchiveFileTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\7z";
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1.7z";

        DefaultUnArchiveContext context = new DefaultUnArchiveContext();
        context.setSourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.setTargetPath(Paths.get(targetPath));
        new ServenZUnArchiveHandler().handle(context);
    }

    @Test
    public void originalTest() throws IOException {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\original.7z";
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1";

        SevenZOutputFile sevenZOutput = new SevenZOutputFile(new File(targetPath));
        SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(new File(sourcePath), "1");
        sevenZOutput.putArchiveEntry(entry);
//        sevenZOutput.write(contentOfEntry);
        sevenZOutput.closeArchiveEntry();
    }

}