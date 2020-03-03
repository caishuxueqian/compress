package com.github.houbb.compress.handler.archive;

import com.github.houbb.compress.exception.CompressRuntimeException;
import com.github.houbb.compress.handler.io.SevenZArchiveInputStream;
import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.util.id.impl.Ids;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ServenZUnArchiveHandler extends AbstractUnArchiveHandler {

    @Override
    protected ArchiveInputStream getArchiveInputStream(InputStream inputStream, String password) {
        try {
            SevenZFile sevenZFile;

            final File sourceFile = inputStreamToFile(inputStream);
            if (StringUtil.isEmpty(password)) {
                sevenZFile = new SevenZFile(sourceFile);
            } else {
                sevenZFile = new SevenZFile(sourceFile, password.toCharArray());
            }
            return new SevenZArchiveInputStream(sevenZFile);
        } catch (IOException e) {
            throw new CompressRuntimeException(e);
        }
    }

    /**
     * 文件输入流转 file
     * <p>
     * https://www.cnblogs.com/asfeixue/p/9065681.html
     *
     * @param inputStream 输入流
     * @return 文件信息
     */
    @CommonEager
    private File inputStreamToFile(final InputStream inputStream) {
        if (ObjectUtil.isNull(inputStream)) {
            return null;
        }

        try {
            File temp = File.createTempFile(Ids.uuid32(), "temp");
            // 退出时删除
            temp.deleteOnExit();

            // 复制文件流信息到 temp 中
            Files.copy(inputStream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return temp;
        } catch (IOException e) {
            throw new CommonRuntimeException(e);
        } finally {
            StreamUtil.closeStream(inputStream);
        }
    }

}
