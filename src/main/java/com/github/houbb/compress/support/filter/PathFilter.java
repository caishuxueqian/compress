package com.github.houbb.compress.support.filter;

import com.github.houbb.compress.annotation.CommonEager;

import java.nio.file.Path;

/**
 * 路径过滤接口
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public interface PathFilter extends IFilter<Path> {
}