package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * zip 解档实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class ZipUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(File sourceFile, String password) {
        try {
            return new ZipArchiveInputStream(new FileInputStream(sourceFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

}
