package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.io.SevenZArchiveInputStream;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.IOException;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ServenZUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(File sourceFile, String password) {
        try {
            SevenZFile sevenZFile;
            if(StringUtil.isEmpty(password)) {
                sevenZFile = new SevenZFile(sourceFile);
            } else {
                sevenZFile = new SevenZFile(sourceFile, password.toCharArray());
            }
            return new SevenZArchiveInputStream(sevenZFile);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

}
