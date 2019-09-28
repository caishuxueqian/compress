# compress

The compress algorithm tool.(常见压缩算法工具类)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/compress/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/compress)
[![Build Status](https://www.travis-ci.org/houbb/compress.svg?branch=master)](https://www.travis-ci.org/houbb/compress?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/compress/badge.svg?branch=master)](https://coveralls.io/github/houbb/compress?branch=master)

## 创作背景

压缩是很常见的一个需求，但是使用 apache 的工具类进行压缩，需要自己处理很多事情。

比如文件是否存在，不存在则需要创建，还要循环处理各种信息，这一点也不优雅。

于是，就基于 apache common-compress 进行封装，便于后期使用。

## 特性

- 支持常见归档压缩算法

- fluent api 设计，优雅方便

# 快速开始

## 环境要求

JDK1.7+

Maven 3.x+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>compress</artifactId>
    <version>0.0.3</version>
</dependency>
```

## 入门例子

所有测试代码，都可以在 test 模块下找到。

### 压缩

将 source 路径的文件，压缩为 target 文件。

指定压缩方式为 ZIP。

```java
public void compressTest() {
    String source = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
    String target = "C:\\Users\\binbin.hou\\Desktop\\1.zip";

    CompressBs.newInstance(CompressTypeEnum.ZIP)
            .source(source)
            .target(target)
            .compress();
}
```

### 解压缩

将 source 路径的文件，解压缩到 target 路径下。

指定解压缩方式为 ZIP。

```java
public void uncompressTest() {
    String source = "C:\\Users\\binbin.hou\\Desktop\\1.zip";
    String target = "C:\\Users\\binbin.hou\\Desktop\\";

    CompressBs.newInstance(CompressTypeEnum.ZIP)
            .source(source)
            .target(target)
            .uncompress();
}
```

# 支持的压缩算法

参见 `CompressTypeEnum` 枚举类。

目前支持如下几种：

```java
public enum CompressTypeEnum {

    /**
     * 归档系列
     */
    JAR,

    /**
     * 7z 压缩方式
     */
    SEVENZ,

    /**
     * zip 压缩方式
     */
    ZIP,

    /**
     * tar 压缩方式
     */
    TAR,
    ;

}
```

# 配置测试

`CompressBs` 引导类还支持更加丰富的属性配置。

使用起来也非常简单方便，如下：

```java
public void configTest() {
    // 指定加密策略
    CompressBs.newInstance(CompressTypeEnum.ZIP)
            // 指定源文件
            .source("")
            // 指定目标文件（夹）
            .target("")
            // 指定密码，默认无。
            .password("")
            // 是否使用相对路径进行处理
            .relativePath(false)
            // 指定解压或者压缩
            .compress();
}
```