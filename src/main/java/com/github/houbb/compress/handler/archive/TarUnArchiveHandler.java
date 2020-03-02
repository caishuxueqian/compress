package com.github.houbb.compress.handler.archive;

import com.github.houbb.heaven.annotation.ThreadSafe;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.InputStream;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class TarUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(InputStream inputStream, String password) {
        return new TarArchiveInputStream(inputStream);
    }

}
