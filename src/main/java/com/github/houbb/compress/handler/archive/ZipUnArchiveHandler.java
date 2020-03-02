package com.github.houbb.compress.handler.archive;

import com.github.houbb.heaven.annotation.ThreadSafe;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.InputStream;

/**
 * zip 解档实现
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ZipUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(InputStream inputStream, String password) {
        return new ZipArchiveInputStream(inputStream);
    }

    @Override
    protected int getEntrySize(ArchiveEntry entry) {
        ZipArchiveEntry archiveEntry = (ZipArchiveEntry) entry;
        return (int) archiveEntry.getDataOffset();
    }

}
