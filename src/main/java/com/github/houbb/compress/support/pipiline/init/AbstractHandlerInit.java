package com.github.houbb.compress.support.pipiline.init;

import com.github.houbb.compress.handler.adaptor.HandlerAdaptor;
import com.github.houbb.compress.support.pipiline.HandlerPipeline;

/**
 * 抽象的 handler 初始化类
 * 1. 保证当前类和 handler 接口的统一化。写到这里，逐渐理解了 Netty 设计的巧妙。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public abstract class AbstractHandlerInit extends HandlerAdaptor {

    /**
     * 初始化 handler
     *
     * @param pipeline 泳道
     */
    protected abstract void initHandler(final HandlerPipeline pipeline);

}
