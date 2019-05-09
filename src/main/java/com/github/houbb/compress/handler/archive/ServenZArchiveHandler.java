package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.io.SevenZArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.IOException;

/**
 * http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveOutputStream
 *
 * [java压缩7z](https://www.jianshu.com/p/f982e797d753)
 * []()
 * @author binbin.hou
 * @since 0.0.1
 */
public class ServenZArchiveHandler extends AbstractArchiveHandler {
    @Override
    protected ArchiveOutputStream getArchiveOutputStream(File targetFile, String password) {
        try {
            SevenZOutputFile sevenZOutput = new SevenZOutputFile(targetFile);
            return new SevenZArchiveOutputStream(sevenZOutput);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    @Override
    protected ArchiveEntry getArchiveEntry(ArchiveOutputStream archiveOutputStream,
                                           File fileToArchive, String entryName) {
        try {
            return archiveOutputStream.createArchiveEntry(fileToArchive, entryName);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

}
