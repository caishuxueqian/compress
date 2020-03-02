package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.heaven.annotation.ThreadSafe;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * zip 解档实现
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ZipUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(File sourceFile, String password) {
        try {
            return new ZipArchiveInputStream(new FileInputStream(sourceFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

    @Override
    protected int getEntrySize(ArchiveEntry entry) {
        ZipArchiveEntry archiveEntry = (ZipArchiveEntry)entry;
        return (int) archiveEntry.getDataOffset();
    }

}
