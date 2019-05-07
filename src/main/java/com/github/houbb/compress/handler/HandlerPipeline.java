package com.github.houbb.compress.handler;

/**
 * 处理器泳道
 * 1. 用于更加灵活的添加 handler，管理 handler 的生命周期
 * 2. 为了简化操作，不要每一个接口都含有 name 这个字段，将使用注解来制定别名。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface HandlerPipeline {
}
