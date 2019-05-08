package com.github.houbb.compress.support.convert.impl;

import com.github.houbb.compress.support.convert.IConvert;

import java.nio.file.Path;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class PathToStringConvert implements IConvert<Path, String> {

    @Override
    public String convert(Path path) {
        return path.toString();
    }

}
