package com.github.houbb.compress.core;

import com.github.houbb.compress.context.ICompressContext;
import com.github.houbb.compress.handler.ICompressHandler;
import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;

/**
 * 压缩引导类
 * 1. 可以连续调用多个的 handler
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class CompressBootstrap {

    /**
     * 处理器
     */
    private ICompressHandler compressHandler;

    /**
     * 处理上下文
     */
    private ICompressContext compressContext;

    /**
     * 构造器私有化
     */
    private CompressBootstrap(){}

    /**
     * 处理器的类
     * @param handlerClass 处理器类
     * @return 引导类本身
     */
    public static CompressBootstrap handler(final Class<? extends ICompressHandler> handlerClass) {
        CompressBootstrap compressBootstrap = new CompressBootstrap();
        compressBootstrap.setHandler(handlerClass);
        return compressBootstrap;
    }

    public CompressBootstrap context(final ICompressContext compressContext) {
        // 一个 handler 是否可以对应多个上下文？？
        // 如果可以，则创建对应的 init，目前不处理。
        this.compressContext = compressContext;
        return this;
    }

    /**
     * 执行归档/解归档操作
     */
    public void compress() {
        this.compressHandler.handle(compressContext);
    }

    /**
     * 处理器的类
     * @param handlerClass 处理器类
     */
    private void setHandler(final Class<? extends ICompressHandler> handlerClass) {
        this.compressHandler = InstanceFactory.getInstance().singleton(handlerClass);
    }

}
