package com.github.houbb.compress.context.adaptor;

import com.github.houbb.compress.context.ICompressContext;

import java.nio.file.Path;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class CompressContextAdaptor implements ICompressContext {

    @Override
    public List<Path> sourcePaths() {
        return null;
    }

    @Override
    public Path sourcePathFirst() {
        return null;
    }

    @Override
    public Path targetPath() {
        return null;
    }

    @Override
    public String password() {
        return null;
    }

    @Override
    public boolean isRelativePath() {
        return false;
    }
}
