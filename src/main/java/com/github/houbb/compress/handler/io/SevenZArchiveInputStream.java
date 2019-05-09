package com.github.houbb.compress.handler.io;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.IOException;

/**
 * 目的：因为 {@link SevenZOutputFile} 这个类没有严格实现 {@link ArchiveOutputStream} 接口，使用组合的方式，使其保持接口的一致性。
 * @author binbin.hou
 * @since 0.0.1
 */
public class SevenZArchiveInputStream extends ArchiveInputStream {

    private SevenZFile sevenZFile;

    public SevenZArchiveInputStream(SevenZFile sevenZFile) {
        this.sevenZFile = sevenZFile;
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return sevenZFile.getNextEntry();
    }

}
