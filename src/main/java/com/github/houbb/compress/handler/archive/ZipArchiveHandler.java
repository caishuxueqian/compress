package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * zip 归档实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class ZipArchiveHandler extends AbstractArchiveHandler {

    @Override
    protected ArchiveOutputStream getArchiveOutputStream(File targetFile, String password) {
        try {
            return new ZipArchiveOutputStream(new FileOutputStream(targetFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

    @Override
    protected ArchiveEntry getArchiveEntry(final ArchiveOutputStream archiveOutputStream,
                                           File fileToArchive, String entryName) {
        return new ZipArchiveEntry(fileToArchive, entryName);
    }

}
