package com.github.houbb.compress.support.convert.impl;

import com.github.houbb.compress.support.convert.IConvert;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class StringToPathConvert implements IConvert<String, Path> {

    @Override
    public Path convert(String s) {
        return Paths.get(s);
    }

}
