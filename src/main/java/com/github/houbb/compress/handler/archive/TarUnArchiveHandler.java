package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class TarUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(File sourceFile, String password) {
        try {
            return new TarArchiveInputStream(new FileInputStream(sourceFile));
        } catch (FileNotFoundException e) {
            throw new CompressRuntimeException(e);
        }
    }

}
