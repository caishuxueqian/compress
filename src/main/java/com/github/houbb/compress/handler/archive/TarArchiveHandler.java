package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.heaven.annotation.ThreadSafe;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class TarArchiveHandler extends AbstractArchiveHandler {

    @Override
    protected ArchiveOutputStream getArchiveOutputStream(File targetFile, String password) {
        try {
            return new TarArchiveOutputStream(new FileOutputStream(targetFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

    @Override
    protected ArchiveEntry getArchiveEntry(final ArchiveOutputStream archiveOutputStream,
                                           File fileToArchive, String entryName) {
        return new TarArchiveEntry(fileToArchive, entryName);
    }

}
