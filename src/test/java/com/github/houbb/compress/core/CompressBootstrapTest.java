package com.github.houbb.compress.core;

import com.github.houbb.compress.context.CompressContextBootstrap;
import com.github.houbb.compress.handler.archive.JarArchiveHandler;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class CompressBootstrapTest {

    @Test
    public void compressTest() {
        String source = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
        String target = "C:\\Users\\binbin.hou\\Desktop\\1.jar";

        CompressBootstrap
                .handler(JarArchiveHandler.class)
                .context(CompressContextBootstrap.source(source).target(target))
                .compress();
    }

}
