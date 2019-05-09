//package com.github.houbb.compress.context.impl;
//
//import com.github.houbb.compress.context.CompressContextBootstrap;
//import com.github.houbb.compress.context.adaptor.CompressContextAdaptor;
//import com.github.houbb.heaven.annotation.NotThreadSafe;
//
//import java.nio.file.Path;
//import java.util.List;
//
///**
// * @author binbin.hou
// * @since 0.0.1
// */
//@NotThreadSafe
//public abstract class BaseCompressContext extends CompressContextAdaptor {
//
//    /**
//     * 引导类
//     */
//    private CompressContextBootstrap bootstrap;
//
//    /**
//     * 默认构造器
//     */
//    public BaseCompressContext() {
//        bootstrap = CompressContextBootstrap.newInstance();
//
//        initContext(bootstrap);
//    }
//
//    /**
//     * 初始化上下文
//     * @param bootstrap 创建引导类
//     */
//    protected abstract void initContext(CompressContextBootstrap bootstrap);
//
//    @Override
//    public List<Path> sourcePaths() {
//        return bootstrap.sourcePaths();
//    }
//
//    @Override
//    public Path sourcePathFirst() {
//        return bootstrap.sourcePathFirst();
//    }
//
//    @Override
//    public Path targetPath() {
//        return bootstrap.targetPath();
//    }
//
//    @Override
//    public String password() {
//        return bootstrap.password();
//    }
//
//    @Override
//    public boolean isRelativePath() {
//        return bootstrap.isRelativePath();
//    }
//}
