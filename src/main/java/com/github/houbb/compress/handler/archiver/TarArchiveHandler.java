//package com.github.houbb.compress.handler.archiver;
//
//import com.github.houbb.compress.context.CompressContext;
//import com.github.houbb.compress.context.IContext;
//import com.github.houbb.compress.context.UnCompressContext;
//import com.github.houbb.compress.exception.CompressRuntimeException;
//import com.github.houbb.compress.util.CompressFileUtil;
//import com.github.houbb.compress.util.PathUtil;
//import com.github.houbb.heaven.annotation.ThreadSafe;
//import org.apache.commons.compress.archivers.ArchiveEntry;
//import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
//import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
//import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * tar 归档实现
// *
// * https://blog.csdn.net/azhegps/article/details/54375466
// *
// * @author binbin.hou
// * @since 0.0.1
// */
//@ThreadSafe
//public class TarArchiveHandler extends CompressHandlerAdaptor {
//
//    /**
//     * 递归处理所有需要归档的文件信息
//     * 1. 所有的 entry 名称，是否应该选择全部，或者相对路径？
//     * @param context 上下文
//     */
//    @Override
//    public void compress(CompressContext context) {
//        final File targetFile = context.getTargetPath().toFile();
//        final List<Path> pathList = buildAllPaths(context);
//        final Path publicParentPath = PathUtil.getPublicParentPath(context.getSourcePaths());
//
//        try(TarArchiveOutputStream outputStream = new TarArchiveOutputStream(new FileOutputStream(targetFile))){
//            // 循环添加 entry 进入归档
//            for(Path path : pathList) {
//                // 文件
//                File fileToArchive = path.toFile();
//                final String entryName = getEntryName(publicParentPath, fileToArchive, context);
//
//                //文件夹
//                if(Files.isDirectory(path)) {
//                    TarArchiveEntry entry = new TarArchiveEntry(fileToArchive, entryName);
//                    entry.setSize(fileToArchive.length());
//                    outputStream.putArchiveEntry(entry);
//                } else {
//                    final byte[] contentOfEntry = Files.readAllBytes(path);
//                    TarArchiveEntry entry = new TarArchiveEntry(fileToArchive, entryName);
//                    entry.setSize(fileToArchive.length());
//                    outputStream.putArchiveEntry(entry);
//                    outputStream.write(contentOfEntry);
//                }
//            }
//            outputStream.closeArchiveEntry();
//        } catch (IOException e) {
//            throw new CompressRuntimeException(e);
//        }
//    }
//
//    @Override
//    public void unCompress(UnCompressContext context) {
//        final File sourceFile = context.getSourcePathFirst().toFile();
//        final File targetDir = context.getTargetPath().toFile();
//
//        try(TarArchiveInputStream inputStream = new TarArchiveInputStream(new FileInputStream(sourceFile))) {
//            ArchiveEntry entry = inputStream.getNextTarEntry();
//            while (entry != null) {
//                // 处理文件夹
//                if (entry.isDirectory()) {
//                    final File dir = buildFile(targetDir, entry);
//                    CompressFileUtil.makeDir(dir);
//                    entry = inputStream.getNextTarEntry();
//                    continue;
//                }
//
//                // 处理单个文件
//                // 创建以来的文件夹。
//                final File file = buildFile(targetDir, entry);
//                CompressFileUtil.makeDir(file.getParentFile());
//                try(FileOutputStream out = new FileOutputStream(file)) {
//                    byte[] content = new byte[(int) entry.getSize()];
//                    inputStream.read(content, 0, content.length);
//                    out.write(content);
//                }
//                entry = inputStream.getNextTarEntry();
//            }
//        } catch (IOException e) {
//            throw new CompressRuntimeException(e);
//        }
//    }
//
//
//    /**
//     * 获取明细的名称
//     * @param publicParentPath 公共的父类路径
//     * @param fileToArchive 归档文件
//     * @param compressContext 上下文
//     * @return 结果
//     */
//    private String getEntryName(final Path publicParentPath,
//                                final File fileToArchive,
//                                final CompressContext compressContext) {
//        if(compressContext.isRelativePath()) {
//            return PathUtil.getRelativePath(publicParentPath, fileToArchive.toPath());
//        } else {
//            // 返回全路径
//            return fileToArchive.getPath();
//        }
//    }
//    /**
//     * 构建目标文件
//     * @param targetDir 目标文件夹
//     * @param entry 明细
//     * @return 结果
//     */
//    private File buildFile(final File targetDir, final ArchiveEntry entry) {
//        return new File(targetDir.getPath() + File.separator + entry.getName());
//    }
//
//    /**
//     * 构建所有的路径信息
//     * @param context 上下文
//     * @return 路径列表
//     */
//    private List<Path> buildAllPaths(final IContext context) {
//        List<Path> allPaths = new ArrayList<>();
//        final List<Path> pathList = context.getSourcePaths();
//
//        for(Path path : pathList) {
//            allPaths.addAll(CompressFileUtil.getPathList(path));
//        }
//        return allPaths;
//    }
//
//
//    /**
//     * 获取公用的根路径
//     *
//     * 一、不使用相对路径，直接返回。
//     *
//     * 二、使用相对路径
//     * （1）只有一个文件，返回单个文件的 parent()
//     * （2）有多个文件
//     *
//     * 1. 获取一个文件的所有路径列表
//     * 2. 获取每一个文件相同路径的交集。选取最长的一个。
//     * 3. 如果都没有相同的，那也是 / root 路径。
//     * @param context 上下文
//     * @return 根文件夹
//     */
//    public Path getPublicParentPath(final CompressContext context) {
//        final boolean isRelativePath = context.isRelativePath();
//        if(!isRelativePath) {
//            return Paths.get("/").getParent();
//        }
//
//        final List<Path> pathList = context.getSourcePaths();
//        // 直接返回第一个元素的父类路径即可、
//        if(pathList.size() == 1) {
//            return context.getSourcePathFirst().getParent();
//        }
//
//        for(Path path : pathList) {
//        }
//
////        CollectionUtil.
//        return null;
//    }
//
//}
