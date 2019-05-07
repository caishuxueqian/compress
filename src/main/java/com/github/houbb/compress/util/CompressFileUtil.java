package com.github.houbb.compress.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 压缩文件工具类
 * @author binbin.hou
 * @since 0.0.1
 */
@Deprecated
public class CompressFileUtil {

    /**
     * 创建文件夹，并且验证是否创建成功
     * 1. 如果文件是文件，则创建其对应的文件夹
     * 2. 如果是文件夹，则判断对应的文件夹是否存在。不存在，则创建。
     * @param dir 文件夹信息
     */
    public static void makeDir( final File dir) {
        if(dir.isDirectory()) {
            if(dir.exists()) {
                return;
            }

            boolean mkResult = dir.mkdirs();
            if(!mkResult) {
                throw new RuntimeException("Mark dir fail for file: " + dir);
            }
        } else {
            File parentDir = dir.getParentFile();
            if(parentDir.exists()) {
                return;
            }

            boolean mkResult = dir.mkdirs();
            if(!mkResult) {
                throw new RuntimeException("Mark dir fail for file: " + dir);
            }
        }
    }


    /**
     * 获取所有对应的文件
     * 1. 如果为文件，直接返回本身
     * 2. 如果为文件夹，则递归获取下面的所有文件信息
     * @param rootPath 根路径
     * @return 文件列表
     */
    public static List<Path> getPathList(final Path rootPath) {
        final List<Path> pathList = new ArrayList<>();

        try {
            if(Files.isDirectory(rootPath)) {
                Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        pathList.add(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        pathList.add(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } else {
                pathList.add(rootPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pathList;
    }

}
