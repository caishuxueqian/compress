package com.github.houbb.compress.handler.other;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 *  正确的案例：
 * https://memorynotfound.com/java-7z-seven-zip-example-compress-decompress-file/
 * @author binbin.hou
 * @since 0.0.1
 */
public class SevenZ {

    public static void compress(String name, File file) throws IOException {
        try (SevenZOutputFile out = new SevenZOutputFile(new File(name))){
            SevenZArchiveEntry entry = out.createArchiveEntry(file, "\\1.txt");
            final byte[] contentOfEntry = Files.readAllBytes(file.toPath());
            out.putArchiveEntry(entry);
            out.write(contentOfEntry);
            out.closeArchiveEntry();
        }
    }

    public static void main(String[] args) throws IOException {
        final String source = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
        final String outPut = "C:\\Users\\binbin.hou\\Desktop\\3.7z";

        compress(outPut, Paths.get(source).toFile());
    }

}
