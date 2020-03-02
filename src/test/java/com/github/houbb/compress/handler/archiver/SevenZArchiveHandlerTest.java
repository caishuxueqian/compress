package com.github.houbb.compress.handler.archiver;

import com.github.houbb.compress.api.impl.CompressContext;
import com.github.houbb.compress.handler.archive.ServenZArchiveHandler;
import com.github.houbb.compress.handler.archive.ServenZUnArchiveHandler;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * [案例](https://memorynotfound.com/java-7z-seven-zip-example-compress-decompress-file/)
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class SevenZArchiveHandlerTest {

    @Test
    public void archiveFileRelativePathTest() {
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\7zFileRelative.7z";

        CompressContext context = new CompressContext();
        context.sourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.targetPath(Paths.get(targetPath));
        context.isRelativePath(true);

        new ServenZArchiveHandler().handle(context);
    }

    @Test
    public void archiveDirNotRelativePathTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\notRelativeDir.7z";
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1";

        CompressContext context = new CompressContext();
        context.sourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.targetPath(Paths.get(targetPath));
        context.isRelativePath(false);

        new ServenZArchiveHandler().handle(context);
    }

    @Test
    public void unArchiveFileTest() {
        final String targetPath = "C:\\Users\\binbin.hou\\Desktop\\7z";
        final String sourcePath = "C:\\Users\\binbin.hou\\Desktop\\1.7z";

        CompressContext context = new CompressContext();
        context.sourcePaths(Arrays.asList(Paths.get(sourcePath)));
        context.targetPath(Paths.get(targetPath));
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
