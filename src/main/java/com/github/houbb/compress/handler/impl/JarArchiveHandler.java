package com.github.houbb.compress.handler.impl;

import com.github.houbb.compress.exception.CompressRuntimeException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * jar 归档实现
 *
 * @author binbin.hou
 * @since 1.0.0
 */
public class JarArchiveHandler extends AbstractArchiveHandler {

    @Override
    protected ArchiveOutputStream getArchiveOutputStream(File targetFile, String password) {
        try {
            return new JarArchiveOutputStream(new FileOutputStream(targetFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

    @Override
    protected ArchiveEntry getArchiveEntry(final ArchiveOutputStream archiveOutputStream,
                                           File fileToArchive, String entryName) {
        JarArchiveEntry jarArchiveEntry = new JarArchiveEntry(entryName);
        jarArchiveEntry.setSize(fileToArchive.length());
        return jarArchiveEntry;
    }

}
