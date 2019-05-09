package com.github.houbb.compress.handler.io;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.IOException;

/**
 * 目的：因为 {@link SevenZOutputFile} 这个类没有严格实现 {@link ArchiveOutputStream} 接口，使用组合的方式，使其保持接口的一致性。
 * @author binbin.hou
 * @since 0.0.1
 */
public class SevenZArchiveOutputStream extends ArchiveOutputStream {

    /**
     * 7z 输出流
     */
    private SevenZOutputFile sevenZOutputFile;

    public SevenZArchiveOutputStream(SevenZOutputFile sevenZOutputFile) {
        this.sevenZOutputFile = sevenZOutputFile;
    }

    @Override
    public void putArchiveEntry(ArchiveEntry entry) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = (SevenZArchiveEntry)entry;
        sevenZOutputFile.putArchiveEntry(sevenZArchiveEntry);
    }

    @Override
    public void closeArchiveEntry() throws IOException {
        sevenZOutputFile.closeArchiveEntry();
    }

    @Override
    public void finish() throws IOException {
        sevenZOutputFile.finish();
    }

    @Override
    public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        return sevenZOutputFile.createArchiveEntry(inputFile, entryName);
    }

    @Override
    public void write(byte[] b) throws IOException {
        sevenZOutputFile.write(b);
    }

    /**
     * 这个流必须关闭，不然信息可能无法刷新。
     * @throws IOException if any
     */
    @Override
    public void close() throws IOException {
        this.sevenZOutputFile.close();
    }
}
