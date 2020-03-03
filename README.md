# compress

The [compress](https://github.com/houbb/compress) algorithm tool.(常见压缩算法工具类)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/compress/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/compress)
[![Build Status](https://www.travis-ci.org/houbb/compress.svg?branch=master)](https://www.travis-ci.org/houbb/compress?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/compress/badge.svg?branch=master)](https://coveralls.io/github/houbb/compress?branch=master)

## 创作背景

压缩是很常见的一个需求，但是使用 apache 的工具类进行压缩，需要自己处理很多事情。

比如文件是否存在，不存在则需要创建，还要循环处理各种信息，这一点也不优雅。

于是，就基于 apache common-compress 进行封装，便于后期使用。

## 特性

- fluent api 设计，优雅灵活

- 极简的工具类 API 设计，一行代码搞定

- 支持常见归档压缩算法

- 返回压缩文件字节信息

- 返回解压文件字节信息

# 快速开始

## 环境要求

JDK1.7+

Maven 3.x+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>compress</artifactId>
    <version>0.0.5</version>
</dependency>
```

## 压缩

将 source 路径的文件，压缩为 target 文件。

默认压缩方式为 ZIP。

```java
final String s = "D:\\github\\compress\\src\\test\\resources\\compress_s.txt";
CompressHelper.compress(s);
```

## 解压缩

将 source 路径的文件，解压缩到 target 路径下。

默认根据压缩文件后缀自动选择算法。

默认位置为压缩文件所在文件夹下。

```java
final String s = "D:\\github\\compress\\src\\test\\resources\\compress_s.zip";

CompressHelper.uncompress(s);
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