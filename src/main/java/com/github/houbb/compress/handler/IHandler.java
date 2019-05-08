package com.github.houbb.compress.handler;

import com.github.houbb.compress.annotation.CommonEager;
import com.github.houbb.compress.context.IContext;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public interface IHandler {

    /**
     * 执行上下文
     * @param context 上下文
     */
    void handle(final IContext context);

}
