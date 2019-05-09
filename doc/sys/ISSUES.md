# 设计思路

filter: 过滤需要压缩/解压的文件

# 转换器

convert:

转换器是单独的。主要用于内部使用。

## 文件相关转换器

比如：

 String=>Path=>File 三者之间的任意转换。

# 调用链

如何 filter==>调用链

filter 应该隶属于每一个处理器。

所以应该在 handler 中进行创建，且只有压缩/解压的 handler 中才有这样的 filter。

=======================

# 多个调用

如何 compress 与 uncompress 之间的任意调用。

处理之后的结果是一个文件路径。

上下文的信息也一直传递下去。

如果任意调用呢？

------------------

compress 压缩器

uncompress 解压器

archive 归档

unarchive 解档

--------------------

# 压缩

## 单个 Handler

x
.source()
.target()
.password()
.removePre()
.filter()

或者这样

```
handler() {
    @override
    initContext() {
        //....
    }
}
```

## 多个调用链

handlers(
one,
two.
three
).fire();

上一个执行的结果会传递到下一个类。

## 中间结果

比如执行 tar.gz 会生成中间结果 tar

用户可以指定是否删除上一个 handler 执行的结果。

针对每一个 handler，因为每一个 handler 都可能是不同的。

# 解压

## 相同压缩

如何 tar.gz

首先归档，然后压缩

## 解压压缩混合

可以压缩，归档，解档，解压，任意调用。

# 调用方式


# 构建者

## 单个的 handler

Handler.builder()
.handler(compress().password().useRelative();
)
.fire();

单个的直接调用。

## 整体调用

调用多个 Handler()

# 内存处理

有时候我们的内容可能不是基于文件的，而是基于内存的。

基于内存的信息，可能有时候用户不希望打开。

# 归档多线程的处理

对于 entry 的添加，可以使用多线程。

同理，解读也可以使用多线程。

这里只是针对归档类。